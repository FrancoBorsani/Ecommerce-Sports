package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    public abstract Producto findByIdProducto(long idProducto);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.visible = true order by p.precio")
    public List<Producto> getAllProductosVisibles();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p WHERE p.categoria_id_categoria = (:categoria) and p.visible = true")
    public abstract List<Producto> findByCategoria(String categoria);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p WHERE p.marca_id_marca = (:marca) and p.visible = true")
    public abstract List<Producto> filterByMarca(String marca);
    
    @Query(nativeQuery=true,value="select * from producto where color = (:color) and descripcioncorta=(:desCorta) and descipcionlarga=(:desLarga) and imagen=(:imagen) and sku=(:sku) and talle=(:talle) and categoria_id_categoria=(:idCat) and marca_id_marca=(:idMarca)")
 	public abstract Producto traerPorVariosAtributos(String color,String desCorta,String desLarga,String imagen,String sku,String talle,long idCat,long idMarca);
    
    @Query("SELECT p FROM Producto p WHERE CONCAT(p.descripcionCorta, ' ', p.descripcionLarga) LIKE %?1%")
    public abstract List<Producto> searchProduct(String keyword);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.visible = true order by p.precio")
    public abstract List<Producto> orderByPriceAsc();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.visible = true order by p.precio desc")
    public abstract List<Producto> orderByPriceDesc();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.visible = true order by p.descripcionCorta")
    public abstract List<Producto> orderByNameAsc();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.visible = true order by p.descripcionCorta desc")
    public abstract List<Producto> orderByNameDesc();

    @Query(nativeQuery=true, value="SELECT * FROM producto" +
            " INNER JOIN tag_productos ON producto.id_producto = productos_id_producto" +
            " WHERE tags_id_tag =  (:idTag) AND producto.visible = 1")
    List<Producto> getRelated(long idTag);
    
    @Modifying
    @Transactional 
    @Query(value="UPDATE Producto p SET p.visible = ?1 WHERE p.idProducto = ?2")
    public int changeVisible(boolean visible,long idProducto);
    
    @Modifying
    @Transactional 
    @Query(value="UPDATE Producto p SET p.descripcionCorta = ?1, p.descripcionLarga = ?2, p.precio = ?3, p.precioEnOferta = ?4, p.color = ?5, p.visible = ?6 WHERE p.idProducto = ?7")
    public int updateProducto(String descripcionCorta,String descripcionLarga,double precio,double precioEnOferta,String color,boolean visible,long idProducto);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.precio != p.precio_en_oferta and p.visible = true")
    public abstract List<Producto> getProductosEnOferta();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p where p.precio = p.precio_en_oferta and p.visible = true")
    public abstract List<Producto> getProductosSinOferta();
    		
}