package com.itm.ecosurprise.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.models.Sede;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.ISede;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

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
    private ProductoService productoService;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private SedeService sedeService;
    @Autowired
    private ISede sedeRepository;
    @Autowired
    private HttpServletRequest request;

    /**
     * Crea un nuevo comerciante junto con su imagen asociada.
     *
     * @param comerciante El comerciante a registrar.
     * @param imagen La imagen asociada al comerciante.
     * @return ResponseEntity con el comerciante creado o un mensaje de error.
     */
    public ResponseEntity<?> crear(Comerciante comerciante, MultipartFile imagen) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                String carpeta = "src/main/resources/static/comerciantes/";
                File directorio = new File(carpeta);
                if (!directorio.exists()) directorio.mkdirs();
                Path ruta = Paths.get(carpeta + nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlImagen = urlBase + "/comerciantes/" + nombreArchivo;

                comerciante.setImagen(urlImagen);
            } else {
                throw new RuntimeException("Imagen vacía");
            }
            Comerciante nuevoComerciante = comercianteRepository.save(comerciante);
            return ResponseEntity.ok(nuevoComerciante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

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
     * @param id ID del comerciante a actualizar.
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
     * Crea un producto asociado a un comerciante.
     *
     * @param idComerciante ID del comerciante.
     * @param producto Producto a registrar.
     * @param imagen Imagen del producto.
     * @return ResponseEntity con el producto creado o mensaje de error.
     */
    public ResponseEntity<?> crearProducto(int idComerciante, Producto producto, MultipartFile imagen) {
        try {
            return ResponseEntity.ok(productoService.crear(idComerciante, producto, imagen));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Crea un teléfono asociado a un comerciante.
     *
     * @param idComerciante ID del comerciante.
     * @param telefono Teléfono a registrar.
     * @return ResponseEntity con el teléfono creado o mensaje de error.
     */
    public ResponseEntity<?> crearTelefono(int idComerciante, Telefono telefono) {
        try {
            return ResponseEntity.ok(telefonoService.crear(idComerciante, telefono));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Crea una sede asociada a un comerciante.
     *
     * @param idComerciante ID del comerciante.
     * @param sede Sede a registrar.
     * @return ResponseEntity con la sede creada o mensaje de error.
     */
    public ResponseEntity<?> crearSede(int idComerciante, Sede sede) {
        try {
            return ResponseEntity.ok(sedeService.crear(idComerciante, sede));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Crea una dirección asociada a una sede de un comerciante.
     *
     * @param idComerciante ID del comerciante.
     * @param idSede ID de la sede.
     * @param direccion Dirección a registrar.
     * @return ResponseEntity con la sede actualizada o mensaje de error.
     */
    public ResponseEntity<?> crearDireccion(int idComerciante, int idSede, Direccion direccion) {
        try {
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            Sede sede = comerciante.getSedes().stream()
                    .filter(s -> s.getIdSede() == idSede)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
            sede.setDireccion(direccionService.crear(direccion));
            return ResponseEntity.ok(sedeRepository.save(sede));
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
     * @param idProducto ID del producto.
     * @return ResponseEntity con el producto encontrado o mensaje de error.
     */
    public ResponseEntity<?> obtenerProducto(int idComerciante, int idProducto) {
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
}
