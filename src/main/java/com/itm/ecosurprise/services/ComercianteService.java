package com.itm.ecosurprise.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IComerciante;

/**
 * Servicio para gestionar las operaciones relacionadas con los comerciantes,
 * incluyendo creación, actualización, eliminación y consulta de comerciantes,
 * productos, teléfonos, sedes y direcciones.
 */
@Service
public class ComercianteService {

    @Autowired
    private IComerciante comercianteRepository;
    @Autowired
    private HttpServletRequest request;

    /**
     * Obtiene la lista de todos los comerciantes registrados.
     *
     * @return ResponseEntity con la lista de comerciantes o mensaje de error.
     */
    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(comercianteRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Obtiene un comerciante por su ID.
     *
     * @param id ID del comerciante a buscar.
     * @return ResponseEntity con el comerciante encontrado o mensaje de error.
     */
    public ResponseEntity<?> obtenerXID(int id) {
        try {
            return ResponseEntity.ok(comercianteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Elimina un comerciante por su ID.
     *
     * @param id ID del comerciante a eliminar.
     * @return ResponseEntity confirmando la eliminación o mensaje de error.
     */
    public ResponseEntity<?> eliminar(int id) {
        try {
            comercianteRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Actualiza la información de un comerciante existente.
     *
     * @param id          ID del comerciante a actualizar.
     * @param comerciante Datos actualizados del comerciante.
     * @return ResponseEntity con el comerciante actualizado o mensaje de error.
     */
    public ResponseEntity<?> actualizar(int id, Comerciante comerciante) {
        try {
            Comerciante comercianteExistente = comercianteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            comercianteExistente.setNombre(comerciante.getNombre());
            return ResponseEntity.ok(comercianteRepository.save(comercianteExistente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Obtiene todos los productos asociados a un comerciante.
     *
     * @param idComerciante ID del comerciante.
     * @return ResponseEntity con la lista de productos o mensaje de error.
     */
    public ResponseEntity<?> obtenerProductos(int idComerciante) {
        try {
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            List<Producto> productos = comerciante.getProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Obtiene un producto específico asociado a un comerciante.
     *
     * @param idComerciante ID del comerciante.
     * @param idProducto    ID del producto.
     * @return ResponseEntity con el producto encontrado o mensaje de error.
     */
    public ResponseEntity<?> obtenerProductoPorId(int idComerciante, int idProducto) {
        try {
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            Producto producto = comerciante.getProductos().stream()
                    .filter(p -> p.getIdProducto() == idProducto)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Error al cargar el producto"));
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> completarRegistro(int id, String nit, MultipartFile camaraComercio, MultipartFile rut) {
        Optional<Comerciante> comercianteOpt = comercianteRepository.findById(id);
        if (comercianteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comerciante no encontrado");
        }
        Comerciante comerciante = comercianteOpt.get();
        comerciante.setNit(nit);
        // Lógica para guardar los PDFs y setear las fileas en el modelo
        String ccPath = guardarArchivo(camaraComercio, "camaraComercio", id);
        String rutPath = guardarArchivo(rut, "file", id);
        comerciante.setCamaraComercio(ccPath);
        comerciante.setRut(rutPath);
        return ResponseEntity.ok(comercianteRepository.save(comerciante));
    }

    private String guardarArchivo(MultipartFile file, String tipo, int id) {
        try {

            if (file != null && !file.isEmpty()) {
                String nombreArchivofile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                String carpetaFile = "src/main/resources/static/usuarios/documentos/";
                File directorioFile = new File(carpetaFile);
                if (!directorioFile.exists())
                    directorioFile.mkdirs();
                Path rutaFile = Paths.get(carpetaFile, nombreArchivofile);
                Files.copy(file.getInputStream(), rutaFile, StandardCopyOption.REPLACE_EXISTING);

                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlfile = urlBase + "/usuarios/documentos/" + nombreArchivofile;

                return urlfile;
            } else {
                throw new RuntimeException("error al subir el file");
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
