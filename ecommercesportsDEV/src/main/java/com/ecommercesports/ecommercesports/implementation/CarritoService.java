package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.models.CarritoModel;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;


@Service("carritoService")
public class CarritoService implements ICarritoService{

	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;
	
	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;
	
	
	@Override
	public List<Carrito> getAll(){
		
		return carritoRepository.findAll();
		
	}
	@Override
	public CarritoModel findByIdCarrito(long idCarrito) {	
		return carritoConverter.entityToModel(carritoRepository.findByIdCarrito(idCarrito));
		
	}
	
	
	@Override
	public CarritoModel insertOrUpdate(CarritoModel carritoModel) {
		
		Carrito carrito = carritoRepository.save(carritoConverter.modelToEntity(carritoModel));
		return carritoConverter.entityToModel(carrito);
		
	}
	
	
	
	@Override
	public boolean remove(long idCarrito) {
		
		try {
			
			carritoRepository.deleteById(idCarrito);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
	
	
}