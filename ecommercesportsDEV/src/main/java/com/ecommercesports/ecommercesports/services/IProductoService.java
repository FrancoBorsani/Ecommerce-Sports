package com.ecommercesports.ecommercesports.services;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ProductoModel;

import java.util.List;

public interface IProductoService {

    public List<Producto> getAll();

    public ProductoModel insertOrUpdate(ProductoModel productoModel);

    public ProductoModel findByIdProducto(long idProducto);

    public boolean remove(long idProducto);

	List<Producto> findByCategoria(String categoria);
	
	List<Producto> filterByMarca(String categoria);
	
}