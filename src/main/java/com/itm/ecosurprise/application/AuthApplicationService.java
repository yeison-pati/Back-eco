package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Comerciante;
import com.itm.ecosurprise.domain.model.Consumidor;
import com.itm.ecosurprise.domain.model.Repartidor;
import com.itm.ecosurprise.domain.model.Telefono;
import com.itm.ecosurprise.domain.model.Usuario;
import com.itm.ecosurprise.domain.port.in.JwtService;
import com.itm.ecosurprise.domain.port.in.LoginUseCase;
import com.itm.ecosurprise.domain.port.in.RegisterUserUseCase;
import com.itm.ecosurprise.domain.port.out.ComercianteRepository;
import com.itm.ecosurprise.domain.port.out.TelefonoRepository;
import com.itm.ecosurprise.domain.port.out.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthApplicationService implements LoginUseCase, RegisterUserUseCase {

    private final UsuarioRepository usuarioRepository;
    private final TelefonoRepository telefonoRepository;
    private final ComercianteRepository comercianteRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AuthApplicationService(UsuarioRepository usuarioRepository, TelefonoRepository telefonoRepository, ComercianteRepository comercianteRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.telefonoRepository = telefonoRepository;
        this.comercianteRepository = comercianteRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Map<String, Object> login(String correo, String contrasena) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo.trim());

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("El correo no está registrado");
        }

        Usuario usuario = usuarioOpt.get();

        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        boolean passwordMatches = passwordEncoder.matches(contrasena.trim(), usuario.getContrasena());

        if (!passwordMatches) {
            throw new RuntimeException("La contraseña es incorrecta");
        }

        String token = jwtService.generateToken(usuario.getCorreo(), usuario.getRol(), usuario.getIdUsuario());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("rol", usuario.getRol());
        response.put("idUsuario", usuario.getIdUsuario());
        response.put("correo", usuario.getCorreo());
        response.put("nombre", usuario.getNombre());
        response.put("telefono", usuario.getTelefono());
        response.put("imagen", usuario.getImagen());
        response.put("message", "Login exitoso");

        if (usuario.getRol().equalsIgnoreCase("COMERCIANTE")) {
            Comerciante comerciante = comercianteRepository.findById(usuario.getIdUsuario()).orElseThrow();
            response.put("nit", comerciante.getNit());
        }

        return response;
    }

    @Override
    public Usuario register(Map<String, Object> userData) {
        String rol = (String) userData.get("rol");
        Usuario usuario;

        switch (rol.toUpperCase()) {
            case "CONSUMIDOR":
                usuario = new Consumidor();
                break;
            case "COMERCIANTE":
                usuario = new Comerciante();
                break;
            case "REPARTIDOR":
                usuario = new Repartidor();
                break;
            default:
                throw new IllegalArgumentException("Rol no válido");
        }

        if (usuarioRepository.findByCorreo((String) userData.get("correo")).isPresent()) {
            throw new RuntimeException("El correo ingresado se encuentra en uso");
        }

        Map<String, String> telefonoToVer = (Map<String, String>) userData.get("telefono");
        if (telefonoToVer != null && usuarioRepository.findByNumero(telefonoToVer.get("numero")).isPresent()) {
            throw new RuntimeException("El teléfono ingresado se encuentra en uso");
        }

        usuario.setNombre((String) userData.get("nombre"));
        usuario.setCorreo((String) userData.get("correo"));
        usuario.setContrasena(passwordEncoder.encode((String) userData.get("contrasena")));
        usuario.setRol(rol);

        Usuario savedUsuario = usuarioRepository.save(usuario);

        Map<String, String> telefonoData = (Map<String, String>) userData.get("telefono");
        if (telefonoData != null) {
            String indicativo = telefonoData.get("indicativo");
            String numero = telefonoData.get("numero");

            if (numero != null && !numero.isEmpty() && indicativo != null && !indicativo.isEmpty()) {
                Telefono telefono = new Telefono();
                telefono.setNumero(numero);
                telefono.setIndicativo(indicativo);
                telefono.setUsuario(savedUsuario);

                Telefono telefonoGuardado = telefonoRepository.save(telefono);

                savedUsuario.setTelefono(telefonoGuardado);
                savedUsuario = usuarioRepository.save(savedUsuario);
            }
        }
        return savedUsuario;
    }
}
