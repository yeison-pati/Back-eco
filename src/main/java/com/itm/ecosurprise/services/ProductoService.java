package com.itm.ecosurprise.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IProducto;

@Service
public class ProductoService {

    @Autowired
    private IProducto productoRepository;
    @Autowired
    private IComerciante comercianteRepository;
    @Autowired
    private IConsumidor consumidorRepository;
    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<?> obtenerTodos(int idConsumidor) {
        try {
            consumidorRepository.findById(idConsumidor)
                    .orElseThrow(() -> new RuntimeException("Consumidor no encontrado"));
            return ResponseEntity.ok(productoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerXID(int idConsumidor, int idProducto) {

        try {
            consumidorRepository.findById(idConsumidor)
                    .orElseThrow(() -> new RuntimeException("consumidor no encontrado"));
            return ResponseEntity.ok(productoRepository.findById(idProducto)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado, intente de nuevo")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public Producto crear(int idComerciante, Producto producto, MultipartFile imagen) throws IOException {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado, intente de nuevo"));

        if (imagen != null && !imagen.isEmpty()) {
            // Generar nombre único para la imagen
            String nombreArchivo = UUID.randomUUID().toString() + "_" + imagen.getOriginalFilename();

            // Ruta local donde se guardará
            String carpeta = "src/main/resources/static/products/";
            File directorio = new File(carpeta);
            if (!directorio.exists()) directorio.mkdirs();

            // Guardar archivo en disco
            Path ruta = Paths.get(carpeta + nombreArchivo);
            Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            // Construir URL pública de acceso
            String urlBase = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String urlImagen = urlBase + "/productos/" + nombreArchivo;

            // Guardar solo la ruta o URL
            producto.setImagen(urlImagen);
        } else {
            throw new RuntimeException("Imagen vacía");
        }

        producto.setComerciante(comerciante);
        return productoRepository.save(producto);
    }

    public Producto actualizar(Producto producto) {

        Producto productoExistente = productoRepository.findById(producto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado, intente de nuevo"));
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        Comerciante comerciante = comercianteRepository.findById(producto.getComerciante().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Comerciante no encontrado"));
        productoExistente.setComerciante(comerciante);
        productoExistente.setPuntuaciones(producto.getPuntuaciones());
        return productoRepository.save(producto);

    }

    public String eliminar(int id) {
        productoRepository.deleteById(id);
        return "Producto eliminado con éxito";
    }
}
