package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Comerciante;
import com.itm.ecosurprise.domain.model.Producto;
import com.itm.ecosurprise.domain.port.in.ComercianteUseCase;
import com.itm.ecosurprise.domain.port.in.FileStorageService;
import com.itm.ecosurprise.domain.port.out.ComercianteRepository;
import com.itm.ecosurprise.domain.port.out.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ComercianteApplicationService implements ComercianteUseCase {

    private final ComercianteRepository comercianteRepository;
    private final ProductoRepository productoRepository;
    private final FileStorageService fileStorageService;


    public ComercianteApplicationService(ComercianteRepository comercianteRepository, ProductoRepository productoRepository, FileStorageService fileStorageService) {
        this.comercianteRepository = comercianteRepository;
        this.productoRepository = productoRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Comerciante> getAll() {
        return comercianteRepository.findAll();
    }

    @Override
    public Optional<Comerciante> getById(int id) {
        return comercianteRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        comercianteRepository.deleteById(id);
    }

    @Override
    public Comerciante update(Comerciante comerciante) {
        return comercianteRepository.save(comerciante);
    }

    @Override
    public List<Producto> getProducts(int idComerciante) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        return comerciante.getProductos();
    }

    @Override
    public Optional<Producto> getProductById(int idComerciante, int idProducto) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        return comerciante.getProductos().stream()
                .filter(p -> p.getIdProducto() == idProducto)
                .findFirst();
    }

    @Override
    public Comerciante completeRegistration(int id, String nit, byte[] camaraComercio, String ccFileName, byte[] rut, String rutFileName) {
        Comerciante comerciante = comercianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        comerciante.setNit(nit);

        if (camaraComercio != null && camaraComercio.length > 0) {
            String ccUrl = fileStorageService.storeFile(camaraComercio, ccFileName, "documentos");
            comerciante.setCamaraComercio(ccUrl);
        }

        if (rut != null && rut.length > 0) {
            String rutUrl = fileStorageService.storeFile(rut, rutFileName, "documentos");
            comerciante.setRut(rutUrl);
        }

        return comercianteRepository.save(comerciante);
    }
}
