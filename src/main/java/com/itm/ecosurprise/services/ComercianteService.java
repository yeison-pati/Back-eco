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
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IComerciante;
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
public class ComercianteService {

    @Autowired
    private IComerciante comercianteRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ITelefono telefonoRepository;
    @Autowired
    private UsuarioDireccionService usuarioDireccionService;

    public ResponseEntity<?> crear(Comerciante comerciante, MultipartFile imagen, MultipartFile rut, MultipartFile cc) {
        try {
            if (imagen != null && !imagen.isEmpty()) {
                String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();
                String carpeta = "src/main/resources/static/comerciantes/imagenes";
                File directorio = new File(carpeta);
                if (!directorio.exists())
                    directorio.mkdirs();
                Path ruta = Paths.get(carpeta + nombreArchivo);
                Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlImagen = urlBase + "/comerciantes/imagenes" + nombreArchivo;

                comerciante.setImagen(urlImagen);
            } else {
                throw new RuntimeException("Imagen vacía");
            }

            if (rut != null && !rut.isEmpty()) {
                String nombreArchivoRut = UUID.randomUUID().toString() + "_" + rut.getOriginalFilename();
                String carpetaRut = "src/main/resources/static/comerciantes/documentos/";
                File directorioRut = new File(carpetaRut);
                if (!directorioRut.exists())
                    directorioRut.mkdirs();
                Path rutaRut = Paths.get(carpetaRut, nombreArchivoRut); 
                Files.copy(rut.getInputStream(), rutaRut, StandardCopyOption.REPLACE_EXISTING);

                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlRut = urlBase + "/comerciantes/documentos/" + nombreArchivoRut;

                comerciante.setRut(urlRut);
            } else {
                throw new RuntimeException("error al subir el rut");
            }

            if (cc != null && !cc.isEmpty()) {
                String nombreArchivoCc = UUID.randomUUID().toString() + "_" + cc.getOriginalFilename();
                String carpetaCc = "src/main/resources/static/comerciantes/documentos/";
                File directorioCc = new File(carpetaCc);
                if (!directorioCc.exists())
                    directorioCc.mkdirs();
                Path rutaCc = Paths.get(carpetaCc, nombreArchivoCc); // Usar carpetaCc
                Files.copy(cc.getInputStream(), rutaCc, StandardCopyOption.REPLACE_EXISTING);

                String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                String urlCc = urlBase + "/comerciantes/documentos/" + nombreArchivoCc;

                comerciante.setCc(urlCc);
            } else {
                throw new RuntimeException("error al subir la camara de comercio");
            }

            Comerciante comercianteExistente = comercianteRepository.save(comerciante);

            // Aquí, crear y asignar el teléfono de forma bidireccional ANTES de guardar
            Telefono telefono = comerciante.getTelefono();
            telefono.setUsuario(comercianteExistente); // bidireccional
            Telefono telefonoGuardado = telefonoRepository.save(telefono); // guardado directo
            comercianteExistente.setTelefono(telefonoGuardado); // actualizar referencia

            List<UsuarioDireccion> direcciones = new ArrayList<>();
            comerciante.getDirecciones().forEach(d -> {
                Direccion dir = new Direccion(); // Asigna valores aquí
                direcciones.add(usuarioDireccionService.crear(comercianteExistente.getIdUsuario(), dir));
            });
            comercianteExistente.setDirecciones(direcciones);
            System.out.println(comercianteExistente);
            return ResponseEntity.ok(comercianteExistente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(comercianteRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerXID(int id) {
        try {
            return ResponseEntity.ok(comercianteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminar(int id) {
        try {
            comercianteRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

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
