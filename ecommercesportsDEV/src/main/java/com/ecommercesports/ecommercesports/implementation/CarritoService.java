package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.models.CarritoModel;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;


@Service("carritoService")
public class CarritoService implements ICarritoService{

	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;

	@Autowired
	@Qualifier("carritoConverter")
	private CarritoConverter carritoConverter;

	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;

	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;

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


	@Override	
	public Carrito carritoDelUserLogueado() {
		int idUserLogueado = userLogueadoService.traerUserLogueado().getId();
		Carrito carrito = carritoRepository.findByIdCarrito(pedidoRepository.traerPedidoByIdUserAndDateNow(idUserLogueado).getCarrito().getIdCarrito());

		return carrito;
	};	

}//Fin class
