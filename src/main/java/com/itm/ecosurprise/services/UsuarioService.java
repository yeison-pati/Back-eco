package com.itm.ecosurprise.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Usuario;
import com.itm.ecosurprise.repositories.IUsuario;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService {
    @Autowired
    private IUsuario usuarioRepository; // Repositorio para gestionar la entidad Usuario
    @Autowired
    private AuthService authService;
    @Autowired
    private HttpServletRequest request; // Para obtener la URL base del servidor

    /**
     * Crea un nuevo consumidor y guarda su imagen asociada en el sistema de
     * archivos.
     * 
     * @param imagen Archivo de imagen para asociar al consumidor.
     * @return ResponseEntity con el consumidor guardado o el error si ocurre una
     *         excepción.
     */// ... existing code ...
    public ResponseEntity<?> setImagen(int idUsuario, MultipartFile imagen, String token) {
        try {
            // Validar el token y obtener el ID del usuario autenticado
            if (token == null || !authService.validateToken(token)) {
                return ResponseEntity.status(401).body("Token inválido o expirado");
            }

            // Obtener el ID del usuario del token
            int idUsuarioAutenticado = authService.getIdFromToken(token);

            // Verificar que el usuario autenticado sea el mismo que intenta modificar
            if (idUsuarioAutenticado != idUsuario) {
                return ResponseEntity.status(403).body("No tienes permiso para modificar la imagen de otro usuario");
            }

            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if (imagen != null && !imagen.isEmpty()) {
                // Generar nombre único para la imagen
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

                // Ruta local donde se guardará la imagen
                String carpeta = "src/main/resources/static/usuarios/";
                File directorio = new File(carpeta);
                if (!directorio.exists())
                    directorio.mkdirs();

                // Guardar archivo en disco
                Path ruta = Paths.get(carpeta + nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

                // Construir URL pública de acceso a la imagen
                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlImagen = urlBase + "/usuarios/" + nombreArchivo;

                // Guardar la URL de la imagen en el objeto Usuario
                usuario.setImagen(urlImagen);

                // Guardar el usuario actualizado
                return ResponseEntity.ok(usuarioRepository.save(usuario));
            } else {
                throw new RuntimeException("Imagen vacía");
            }
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}