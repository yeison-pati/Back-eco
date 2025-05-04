package com.itm.ecosurprise.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.ITelefono;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumidorService {

    @Autowired
    private IConsumidor consumidorRepository; // Repositorio para gestionar la entidad Consumidor
    @Autowired
    private HttpServletRequest request; // Para obtener la URL base del servidor
    @Autowired
    private ITelefono telefonoRepository;
    @Autowired
    private UsuarioDireccionService usuarioDireccionService;

    public ResponseEntity<?> crear(Consumidor consumidor, MultipartFile imagen) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                String carpeta = "src/main/resources/static/consumidores/imagenes/";
                File directorio = new File(carpeta);
                if (!directorio.exists())
                    directorio.mkdirs();
                Path ruta = Paths.get(carpeta, nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlImagen = urlBase + "/consumidores/imagenes/" + nombreArchivo;
                consumidor.setImagen(urlImagen);
            } else {
                throw new RuntimeException("Imagen vacía");
            }

            // Guardar consumidor sin teléfono ni direcciones aún
            Consumidor consumidorExistente = consumidorRepository.save(consumidor);

            // Aquí, crear y asignar el teléfono de forma bidireccional ANTES de guardar
            Telefono telefono = consumidor.getTelefono();
            telefono.setUsuario(consumidorExistente); // bidireccional
            Telefono telefonoGuardado = telefonoRepository.save(telefono); // guardado directo
            consumidorExistente.setTelefono(telefonoGuardado); // actualizar referencia

            List<UsuarioDireccion> direcciones = new ArrayList<>();
            consumidor.getDirecciones().forEach(d -> {
                Direccion dir = new Direccion(); // Asigna valores aquí
                direcciones.add(usuarioDireccionService.crear(consumidorExistente.getIdUsuario(), dir));
            });
            consumidorExistente.setDirecciones(direcciones);

            return ResponseEntity.ok(consumidorRepository.save(consumidorExistente));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(consumidorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    public ResponseEntity<?> obtenerXID(int idConsumidor) {
        try {
            return ResponseEntity.ok(consumidorRepository.findById(idConsumidor)
                    .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor)));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    public ResponseEntity<?> eliminar(int id) {
        try {
            consumidorRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Manejo de errores
        }
    }

    public ResponseEntity<?> actualizar(int id, Consumidor consumidor) {
        try {
            Consumidor consumidorexistente = consumidorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
            consumidorexistente.setNombre(consumidor.getNombre()); // Actualizar el nombre del consumidor
            return ResponseEntity.ok(consumidorRepository.save(consumidorexistente)); // Guardar el consumidor
                                                                                      // actualizado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Manejo de errores
        }
    }


}
