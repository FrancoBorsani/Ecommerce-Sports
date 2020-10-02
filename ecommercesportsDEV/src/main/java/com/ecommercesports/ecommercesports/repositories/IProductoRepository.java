package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    public abstract Producto findByIdProducto(long idProducto);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p WHERE p.categoria_id_categoria = (:categoria)")
    public abstract List<Producto> findByCategoria(String categoria);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p WHERE p.marca_id_marca = (:marca)")
    public abstract List<Producto> filterByMarca(String marca);
    
    @Query("SELECT p FROM Producto p WHERE CONCAT(p.descripcionCorta, ' ', p.descripcionLarga) LIKE %?1%")
    public abstract List<Producto> searchProduct(String keyword);
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p order by p.precio")
    public abstract List<Producto> orderByPriceAsc();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p order by p.precio desc")
    public abstract List<Producto> orderByPriceDesc();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p order by p.descripcionCorta")
    public abstract List<Producto> orderByNameAsc();
    
    @Query(nativeQuery=true,value="SELECT * FROM Producto as p order by p.descripcionCorta desc")
    public abstract List<Producto> orderByNameDesc();
    
       
}