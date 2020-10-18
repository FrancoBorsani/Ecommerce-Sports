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

import com.ecommercesports.ecommercesports.models.PedidoModel;
import com.ecommercesports.ecommercesports.services.IPedidoService;

@RestController
@RequestMapping("api/checkout")
public class CheckoutRestController {
	
	@Autowired
    @Qualifier("pedidoService")
    private IPedidoService pedidoService;
	
	@GetMapping("/getAllPedidos")
	public ResponseEntity<?> getAllProductos() {
		return ResponseEntity.ok(pedidoService.getAll());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProducto(@RequestBody PedidoModel pedidoModel) {
		
		System.out.println(pedidoModel);
		
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/getCostoEnvio/{empresa}")
	public ResponseEntity<?> calcularCostoEnvio(@PathVariable("empresa") String empresa) {
						
		double largo = 20;
		double alto = 20;
		double ancho = 25;
		
		double precioProducto = 0;
				
		double pesoDefinitivo = 0;
				
		/* https://www.mercadolibre.com.ar/ayuda/C-mo-calcular-el-peso-de-tu-en_4420 */
		
		/* Ya conozco el peso físico y el volumétrico, ¿cuál uso para calcular el costo de mi envío?

		Si el peso volumétrico es menor o igual a 2, usá el peso físico de tu producto.
		
		Si el peso volumétrico es mayor a 2, usá el que sea mayor (físico o volumétrico).
		
		En nuestro ejemplo, el peso volumétrico es mayor a 2, por eso calcularemos el costo de envío usando ese peso. */
				
		
		double pesoVolumetrico = ( largo * alto * ancho ) / 4000;
		
		if(pesoVolumetrico<=precioProducto) {
			pesoDefinitivo = precioProducto;
		} else {
			pesoDefinitivo = (pesoVolumetrico>precioProducto)? pesoVolumetrico : precioProducto;
		}
		/*
		if(pesoDefinitivo>=0 && pesoDefinitivo < 0.5) {
			pedidoService.getCostoEnvio(empresa,);
		} else if (pesoDefinitivo>=0.5 && pesoDefinitivo < 1) {
			
		} else if (pesoDefinitivo>=1 && pesoDefinitivo < 2) {
			
		} else if (pesoDefinitivo>=2 && pesoDefinitivo < 3) {
			
		} else if (pesoDefinitivo>=3 && pesoDefinitivo < 5) {
			
		} else if (pesoDefinitivo>=3 && pesoDefinitivo < 5) {
			
		} else if (pesoDefinitivo>=5 && pesoDefinitivo < 10) {
			
		} else if (pesoDefinitivo>=10 && pesoDefinitivo < 15) {
			
		} else if (pesoDefinitivo>=15 && pesoDefinitivo < 20) {
			
		} else if (pesoDefinitivo>=20 && pesoDefinitivo < 25) {
			
		}
		*/
		
		String precio = "precio";
		
		return ResponseEntity.ok(pedidoService.getCostoEnvio(empresa));
	}
	

}
