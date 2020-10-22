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
        return new ProductoModel(producto.getIdProducto(),producto.getPrecio(), producto.getPrecioEnOferta(), producto.getColor(),
                producto.getDescripcionCorta(), producto.getDescripcionLarga(),producto.getSku(), producto.getTalle(),
                producto.getTotalPuntaje(), producto.getCantidadValoraciones(), producto.getPeso(), producto.getTags(), producto.getImagen(), producto.isVisible());}

    public Producto modelToEntity(ProductoModel productoModel) {
        return new Producto(productoModel.getIdProducto(),productoModel.getPrecio(),productoModel.getPrecioEnOferta(),productoModel.getColor(),
        		productoModel.getDescripcionCorta(),productoModel.getDescripcionLarga(),productoModel.getSku(),productoModel.getTalle(),
        		productoModel.getTotalPuntaje(),productoModel.getCantidadValoraciones(),productoModel.getPeso(),productoModel.getTags(),productoModel.getImagen(),productoModel.isVisible());
    }
}