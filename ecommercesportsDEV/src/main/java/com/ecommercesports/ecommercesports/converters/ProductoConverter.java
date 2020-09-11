package com.ecommercesports.ecommercesports.converters;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("productoConverter")
public class ProductoConverter {

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;

    public ProductoModel entityToModel(Producto producto) {
        return new ProductoModel(producto.getPrecio(), producto.getColor(), producto.getDescripcionCorta(),
                producto.getDescripcionLarga(), producto.getMarca(), producto.getSku(), producto.getTalle());
    }

    public Producto modelToEntity(ProductoModel productoModel) {
        return new Producto(productoModel.getPrecio(), productoModel.getColor(), productoModel.getDescripcionCorta(),
                productoModel.getDescripcionLarga(), productoModel.getMarca(), productoModel.getSku(), productoModel.getTalle());
    }
}