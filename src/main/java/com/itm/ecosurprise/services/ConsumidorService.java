package com.itm.ecosurprise.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.repositories.IConsumidor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ConsumidorService {

    @Autowired
    private IConsumidor consumidorRepository; // Repositorio para gestionar la entidad Consumidor
    @Autowired
    private HttpServletRequest request; // Para obtener la URL base del servidor


    /**
     * Crea un nuevo consumidor y guarda su imagen asociada en el sistema de archivos.
     * @param consumidor Objeto Consumidor con los datos a guardar.
     * @param imagen Archivo de imagen para asociar al consumidor.
     * @return ResponseEntity con el consumidor guardado o el error si ocurre una excepción.
     */
    public ResponseEntity<?> crear(Consumidor consumidor, MultipartFile imagen) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                // Generar nombre único para la imagen
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

                // Ruta local donde se guardará la imagen
                String carpeta = "src/main/resources/static/comerciantes/";
                File directorio = new File(carpeta);
                if (!directorio.exists()) directorio.mkdirs(); // Crear directorio si no existe

                // Guardar archivo en disco
                Path ruta = Paths.get(carpeta + nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

                // Construir URL pública de acceso a la imagen
                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlImagen = urlBase + "/comerciantes/" + nombreArchivo;

                // Guardar la URL de la imagen en el objeto Consumidor
                consumidor.setImagen(urlImagen);
            } else {
                throw new RuntimeException("Imagen vacía");
            }

            // Guardar el consumidor en la base de datos
            return ResponseEntity.ok(consumidorRepository.save(consumidor));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Obtiene todos los consumidores registrados en la base de datos.
     * @return ResponseEntity con la lista de consumidores o el error si ocurre una excepción.
     */
    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(consumidorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Obtiene un consumidor por su ID.
     * @param idConsumidor ID del consumidor a buscar.
     * @return ResponseEntity con el consumidor encontrado o el error si no se encuentra.
     */
    public ResponseEntity<?> obtenerXID(int idConsumidor){
        try {
            return ResponseEntity.ok(consumidorRepository.findById(idConsumidor)
            .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor)));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Elimina un consumidor por su ID.
     * @param id ID del consumidor a eliminar.
     * @return ResponseEntity con un mensaje de éxito o el error si ocurre una excepción.
     */
    public ResponseEntity<?> eliminar(int id) {
        try {
            consumidorRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Actualiza los datos de un consumidor existente.
     * @param id ID del consumidor a actualizar.
     * @param consumidor Objeto con los nuevos datos del consumidor.
     * @return ResponseEntity con el consumidor actualizado o el error si ocurre una excepción.
     */
    public ResponseEntity<?> actualizar(int id, Consumidor consumidor) {
        try {
            Consumidor consumidorexistente = consumidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
            consumidorexistente.setNombre(consumidor.getNombre()); // Actualizar el nombre del consumidor
            return ResponseEntity.ok(consumidorRepository.save(consumidorexistente)); // Guardar el consumidor actualizado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Manejo de errores
        }
    }

}
