package com.ecommercesports.ecommercesports.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ProductoModel;

@Component("productoConverter")
public class ProductoConverter {

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;

    public ProductoModel entityToModel(Producto producto) {
        return new ProductoModel(producto.getIdProducto(),producto.getPrecio(), producto.getColor(),
                producto.getDescripcionCorta(), producto.getDescripcionLarga(),producto.getSku(), producto.getTalle(),
                producto.getTotalPuntaje(), producto.getCantidadValoraciones(), producto.getTags());    }

    public Producto modelToEntity(ProductoModel productoModel) {
        return new Producto(productoModel.getIdProducto(),productoModel.getPrecio(), productoModel.getColor(),
                productoModel.getDescripcionCorta(), productoModel.getDescripcionLarga(), productoModel.getSku(),
                productoModel.getTalle(), productoModel.getTotalPuntaje(), productoModel.getCantidadValoraciones(),
                productoModel.getTags());
    }
}