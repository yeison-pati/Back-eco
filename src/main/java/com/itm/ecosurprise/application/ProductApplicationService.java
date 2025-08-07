package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Comerciante;
import com.itm.ecosurprise.domain.model.Producto;
import com.itm.ecosurprise.domain.port.in.FileStorageService;
import com.itm.ecosurprise.domain.port.in.ProductUseCase;
import com.itm.ecosurprise.domain.port.out.ComercianteRepository;
import com.itm.ecosurprise.domain.port.out.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ProductApplicationService implements ProductUseCase {

    private final ProductoRepository productoRepository;
    private final ComercianteRepository comercianteRepository;
    private final FileStorageService fileStorageService;

    public ProductApplicationService(ProductoRepository productoRepository, ComercianteRepository comercianteRepository, FileStorageService fileStorageService) {
        this.productoRepository = productoRepository;
        this.comercianteRepository = comercianteRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto createProduct(int idComerciante, Producto producto, byte[] imagen, String fileName) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        producto.setComerciante(comerciante);

        if (imagen != null && imagen.length > 0) {
            String imageUrl = fileStorageService.storeFile(imagen, fileName, "productos");
            producto.setImagen(imageUrl);
        }

        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProduct(int idComerciante, int idProducto, Producto producto, byte[] imagen, String fileName) {
        Producto existingProduct = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));

        existingProduct.setNombre(producto.getNombre());
        existingProduct.setDescripcion(producto.getDescripcion());
        existingProduct.setTipo(producto.getTipo());
        existingProduct.setPrecio(producto.getPrecio());
        existingProduct.setStock(producto.getStock());
        existingProduct.setComerciante(comerciante);

        if (imagen != null && imagen.length > 0) {
            String imageUrl = fileStorageService.storeFile(imagen, fileName, "productos");
            existingProduct.setImagen(imageUrl);
        }

        return productoRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(int id) {
        productoRepository.deleteById(id);
    }
}
