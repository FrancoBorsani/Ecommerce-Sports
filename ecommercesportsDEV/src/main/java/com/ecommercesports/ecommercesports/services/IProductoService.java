package com.ecommercesports.ecommercesports.services;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ProductoModel;

import java.util.List;

public interface IProductoService {

    public List<Producto> getAll();

    public ProductoModel insertOrUpdate(ProductoModel productoModel);

    public ProductoModel findByIdProducto(long idProducto);

    public boolean remove(long idProducto);

    public List<Producto> findByCategoria(String categoria);
	
    public List<Producto> filterByMarca(String categoria);
    
    public List<Producto> searchProduct(String keyword);
	
}