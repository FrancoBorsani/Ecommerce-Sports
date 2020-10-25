package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.entities.Marca;
import com.ecommercesports.ecommercesports.repositories.IMarcaRepository;
import com.ecommercesports.ecommercesports.services.IMarcaService;

@Service("marcaService")
public class MarcaService implements IMarcaService{
	
	@Autowired
    @Qualifier("marcaRepository")
    private IMarcaRepository marcaRepository;

	@Override
	public List<Marca> getAll() {
		// TODO Auto-generated method stub
		return marcaRepository.findAll();
	}

    @Override 
    public Marca traerMarcaPorNombre(String nombreMarca) {
    	Marca marca = null;
        try {
            return marcaRepository.traerMarcaPorNombre(nombreMarca);
        } catch (Exception e) {
            return marca;
        }
    }

    @Override
    public Marca traerMarcaPorNombreO_Crear(String nombreMarca) {
    	Marca marca = traerMarcaPorNombre(nombreMarca);
    	if(marca==null) {
    		marca = new Marca();
    		marca.setNombre(nombreMarca);
    		marcaRepository.save(marca);
    		marca = marcaRepository.findAll().get(marcaRepository.findAll().size()-1);
    		
    	}
    return marca;
    }
	
}//Fin class
