package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Orden;
import com.itm.ecosurprise.domain.model.PreparationOrders;

public interface PreparationOrdersUseCase {
    PreparationOrders getPreparationOrders(int idComerciante);
    void addOrderToPreparation(int idComerciante, int idOrden);
    Orden getOrderFromPreparation(int idComerciante, int idOrden);
}
