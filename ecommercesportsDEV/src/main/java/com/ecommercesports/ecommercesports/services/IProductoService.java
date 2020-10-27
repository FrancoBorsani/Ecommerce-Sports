package com.ecommercesports.ecommercesports.services;

import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ProductoModel;
import com.ecommercesports.ecommercesports.models.RegistroExcelModel;

import java.util.List;


public interface IProductoService {

    public List<Producto> getAll();
    
    public List<Producto> getAllProductosVisibles();

    public ProductoModel insertOrUpdate(ProductoModel productoModel);

    public ProductoModel findByIdProducto(long idProducto);

    public boolean remove(long idProducto);

    public List<Producto> findByCategoria(String categoria);
	
    public List<Producto> filterByMarca(String categoria);
    
    public List<Producto> searchProduct(String keyword);
    
    public abstract List<Producto> orderByPriceAsc();
    
    public abstract List<Producto> orderByPriceDesc();
    
    public abstract List<Producto> orderByNameAsc();
    
    public abstract List<Producto> orderByNameDesc();
    
	public List<Producto> productosDestacados(String order_filter);

    public List<Producto> getRelated(long idProducto);
    
    public void changeVisible(boolean visible, long idProducto);
    
    public void updateProducto(String descripcionCorta,String descripcionLarga,double precio,double precioEnOferta,String color,boolean visible,long idProducto);
    
    public List<Producto> getProductosEnOferta();
    
	public List<RegistroExcelModel> traerRegistrosEnlistaModel();
	
	public Producto cagarProductoEnBDConMarcaYCategoriaYtraer(Producto producto, String nombreCategoria, String nombreMarca);
	
	public Producto cargaParcialDeProducto(RegistroExcelModel rE);
	
	public boolean hayCamposEnNULL(RegistroExcelModel rE);
	
	public Producto traerSiExisteElProductoEnBD(Producto producto, String nombreCategoria, String nombreMarca);
	
	public abstract List<Producto> getProductosSinOferta();
    
}//Fin class