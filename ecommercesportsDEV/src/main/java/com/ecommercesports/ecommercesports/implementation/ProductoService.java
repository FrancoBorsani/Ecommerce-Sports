package com.ecommercesports.ecommercesports.implementation;

import com.ecommercesports.ecommercesports.converters.ProductoConverter;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.models.ProductoModel;
import com.ecommercesports.ecommercesports.repositories.IProductoRepository;
import com.ecommercesports.ecommercesports.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productoService")
public class ProductoService implements IProductoService {

    @Autowired
    @Qualifier("productoRepository")
    private IProductoRepository productoRepository;

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoModel findByIdProducto(long idProducto) {
        return productoConverter.entityToModel(productoRepository.findByIdProducto(idProducto));
    }

    @Override
    public ProductoModel insertOrUpdate(ProductoModel productoModel) {
        Producto producto = productoRepository.save(productoConverter.modelToEntity(productoModel));
        return productoConverter.entityToModel(producto);
    }

    @Override
    public boolean remove(long idProducto) {
        try {
            productoRepository.deleteById(idProducto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}