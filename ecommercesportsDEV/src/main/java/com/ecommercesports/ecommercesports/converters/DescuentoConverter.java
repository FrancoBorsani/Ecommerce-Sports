package com.ecommercesports.ecommercesports.converters;

import com.ecommercesports.ecommercesports.entities.Descuento;
import com.ecommercesports.ecommercesports.models.DescuentoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DescuentoConverter {

    @Autowired
    @Qualifier("descuentoConverter")
    private DescuentoConverter descuentoConverter;

    public DescuentoModel entityToModel(Descuento descuento) {
        return new DescuentoModel(descuento.getIdDescuento(), descuento.getCodigo(), descuento.getPorcentaje());
    }

    public Descuento modelToEntity(DescuentoModel descuentoModel) {
        return new Descuento(descuentoModel.getIdDescuento(), descuentoModel.getCodigo(), descuentoModel.getPorcentaje());
    }
}
