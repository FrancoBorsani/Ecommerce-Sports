package com.ecommercesports.ecommercesports.services;

import com.ecommercesports.ecommercesports.models.DescuentoModel;

public interface IDescuentoService {

    DescuentoModel insertOrUpdate(DescuentoModel descuentoModel);
    boolean remove(long idDescuento);
    DescuentoModel findByIdDescuento(long idDescuento);
    DescuentoModel findByCode(String codigo);
}
