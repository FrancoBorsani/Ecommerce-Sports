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
	
}
