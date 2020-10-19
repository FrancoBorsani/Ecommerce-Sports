package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.entities.Categoria;
import com.ecommercesports.ecommercesports.repositories.ICategoriaRepository;
import com.ecommercesports.ecommercesports.services.ICategoriaService;

@Service("categoriaService")
public class CategoriaService implements ICategoriaService{
	
	@Autowired
    @Qualifier("categoriaRepository")
    private ICategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

    @Override 
    public Categoria traerCategoriaPorNombre(String nombreCategoria) {
    	Categoria categoria = null;
        try {
            return categoriaRepository.traerCategoriaPorNombre(nombreCategoria);
        } catch (Exception e) {
            return categoria;
        }
    }
	
    @Override
    public Categoria traerCategoriaPorNombreO_Crear(String nombreCategoria) {
    	Categoria categoria = traerCategoriaPorNombre(nombreCategoria);
    	if(categoria==null) {
    		categoria = new Categoria();
    		categoria.setNombre(nombreCategoria);
    		categoriaRepository.save(categoria);
    		categoria = categoriaRepository.findAll().get(categoriaRepository.findAll().size()-1);
    	}
    return categoria;
    }
	
}//Fin class
