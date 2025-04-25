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
    private IConsumidor consumidorRepository;
    @Autowired
    private HttpServletRequest request;


    public ResponseEntity<?> crear(Consumidor consumidor, MultipartFile imagen) {
        try {

            if (imagen != null && !imagen.isEmpty()) {
                // Generar nombre único para la imagen
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

                // Ruta local donde se guardará
                String carpeta = "src/main/resources/static/comerciantes/";
                File directorio = new File(carpeta);
                if (!directorio.exists()) directorio.mkdirs();

                // Guardar archivo en disco
                Path ruta = Paths.get(carpeta + nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

                // Construir URL pública de acceso
                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlImagen = urlBase + "/comerciantes/" + nombreArchivo;

                // Guardar solo la ruta o URL
                consumidor.setImagen(urlImagen);
            } else {
                throw new RuntimeException("Imagen vacía");
            }
            return ResponseEntity.ok(consumidorRepository.save(consumidor));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(consumidorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerXID(int idConsumidor){
        try {
            return ResponseEntity.ok(consumidorRepository.findById(idConsumidor)
            .orElseThrow(()-> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor )));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminar(int id) {
        try {
            consumidorRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    public ResponseEntity<?> actualizar(int id, Consumidor consumidor) {
        try {
            Consumidor consumidorexistente = consumidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
        consumidorexistente.setNombre(consumidor.getNombre());
        return ResponseEntity.ok(consumidorRepository.save(consumidorexistente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
/*
 * public String actualizarFoto(int idConsumidor, MultipartFile file) {
        try {
            Consumidor consumidor = consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));

            // Convertir la imagen a byte[]
            byte[] fotoBytes = file.getBytes();
            consumidor.setImagen(fotoBytes);

            // Guardar el consumidor con la nueva foto
            consumidorRepository.save(consumidor);

            return "Foto actualizada con éxito";
        } catch (Exception e) {
            return "Error al cargar la imagen";
        }
    }
 */