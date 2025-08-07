package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Comerciante;
import com.itm.ecosurprise.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ComercianteUseCase {
    List<Comerciante> getAll();
    Optional<Comerciante> getById(int id);
    void deleteById(int id);
    Comerciante update(Comerciante comerciante);
    List<Producto> getProducts(int idComerciante);
    Optional<Producto> getProductById(int idComerciante, int idProducto);
    Comerciante completeRegistration(int id, String nit, byte[] camaraComercio, String ccFileName, byte[] rut, String rutFileName);
}
