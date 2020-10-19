package com.ecommercesports.ecommercesports.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.repositories.ICarritoRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IProductoRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;

@RestController
@RequestMapping("api/checkout")
public class CheckoutRestController {
	
	@Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;
	
	@Autowired
	@Qualifier("carritoService")
	private ICarritoService carritoService;

	@Autowired
	@Qualifier("carritoRepository")
	private ICarritoRepository carritoRepository;
	
	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
	
	@Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;
	
	@GetMapping("/getAllPedidos")
	public ResponseEntity<?> getAllProductos() {
		return ResponseEntity.ok(pedidoService.getAll());
	}
	
	@PutMapping("/updateCostoEnvio/{empresa}")
	public ResponseEntity<?> updateProducto(@RequestBody PedidoModel pedido, @PathVariable("empresa") String empresa) {
		
		pedidoService.updateCostoEnvio((calcularCostoEnvio(empresa)),pedido.getIdPedido());
						
		return ResponseEntity.ok(calcularCostoEnvio(empresa));
	}
	
	public double calcularCostoEnvio(String empresa) {
						
		double largo = 20;
		double alto = 20;
		double ancho = 25;
		
		double pesoReal = calcularCostoReal();
				
		double pesoDefinitivo = 0;
		
				
		/* https://www.mercadolibre.com.ar/ayuda/C-mo-calcular-el-peso-de-tu-en_4420 */
		
		/* Ya conozco el peso físico y el volumétrico, ¿cuál uso para calcular el costo de mi envío?

		Si el peso volumétrico es menor o igual a 2, usá el peso físico de tu producto.
		
		Si el peso volumétrico es mayor a 2, usá el que sea mayor (físico o volumétrico).
		
		En nuestro ejemplo, el peso volumétrico es mayor a 2, por eso calcularemos el costo de envío usando ese peso. */
				
		
		double pesoVolumetrico = ( largo * alto * ancho ) / 4000;
		
		if(pesoVolumetrico<=pesoReal) {
			pesoDefinitivo = pesoReal;
		} else {
			pesoDefinitivo = (pesoVolumetrico>pesoReal)? pesoVolumetrico : pesoReal;
		}
		
		int nroColumna = 0;
		
		if(pesoDefinitivo>=0 && pesoDefinitivo < 0.5) {
			nroColumna = 1;
		} else if (pesoDefinitivo>=0.5 && pesoDefinitivo < 1) {
			nroColumna = 2;
		} else if (pesoDefinitivo>=1 && pesoDefinitivo < 2) {
			nroColumna = 3;
		} else if (pesoDefinitivo>=2 && pesoDefinitivo < 3) {
			nroColumna = 4;
		} else if (pesoDefinitivo>=3 && pesoDefinitivo < 5) {
			nroColumna = 5;
		} else if (pesoDefinitivo>=3 && pesoDefinitivo < 5) {
			nroColumna = 5;
		} else if (pesoDefinitivo>=5 && pesoDefinitivo < 10) {
			nroColumna = 6;
		} else if (pesoDefinitivo>=10 && pesoDefinitivo < 15) {
			nroColumna = 7;
		} else if (pesoDefinitivo>=15 && pesoDefinitivo < 20) {
			nroColumna = 8;
		} else if (pesoDefinitivo>=20 && pesoDefinitivo < 25) {
			nroColumna = 9;
		}

		return pedidoService.getCostoEnvio(empresa,nroColumna);
	}
	
	public double calcularCostoReal() {
		double costoReal = 0;
				
		for(Item i : carritoService.carritoDelUserLogueadoParaController().getListaItems()) {
			costoReal = costoReal + i.getCantidad() * i.getProducto().getPeso();
		}
				
		return costoReal;
	
	}
	

}
