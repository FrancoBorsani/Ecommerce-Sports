package com.ecommercesports.ecommercesports.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercesports.ecommercesports.models.ProductoModel;
import com.ecommercesports.ecommercesports.services.IProductoService;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
	
	@Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
	
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProductos() {
		return ResponseEntity.ok(productoService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProducto(@PathVariable("id") long idProducto) {		
		return ResponseEntity.ok(productoService.findByIdProducto(idProducto));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProducto(@PathVariable("id") long idProducto,@RequestBody ProductoModel productoModel) {
		productoService.updateProducto(productoModel.getDescripcionCorta(),productoModel.getDescripcionLarga(), productoModel.getPrecio(), productoModel.getPrecioEnOferta(), productoModel.getColor(), productoModel.isVisible(), idProducto);
		return ResponseEntity.ok(true);
	}
	
	@PatchMapping("/visibility/{id}")
	public ResponseEntity<?> changeVisibility(@PathVariable("id") long idProducto) {
								
		ProductoModel producto = productoService.findByIdProducto(idProducto);
		
		productoService.changeVisible(!producto.isVisible(), producto.getIdProducto());
		
		String result = "El " + producto.getDescripcionLarga() + " ahora " + (producto.isVisible()? "no es visible en el catalogo": "es visible en el catalogo");		
    	    			
		return ResponseEntity.ok(result);
	}		

}
