$(document).ready(function () {
	
        $(".visible").on("click",function () {
        	
        	var pedidoModel = {};
        	var idProducto = 1;

        	pedidoModel["domicilio"] = "Domicilio1";
        	pedidoModel["cantidad"] = 23;
        	pedidoModel["metodoPago"] = "MetodoDePago1";

        	$.ajax({
				type : "PUT",
				url : "/api/checkout/update/"+ idProducto,
				contentType: "application/json",
				data: JSON.stringify(pedidoModel),
		        cache: false,
		        timeout: 600000,
				success : function(result) {
					window.location.reload();
				},
				error : function(e) {
					alert("Error!");
				}
			});

        });
        
      $("input[name='payment']").on("click",function () {
        	
    	  var empresa = $(this).attr('class'); 

    	  var pedidoModel = {};
    	  
    	  pedidoModel["idPedido"] = $(".idCart").attr('id');
    	  
    	  $.ajax({
				type : "PUT",
				url : "/api/checkout/updateCostoEnvio/" + empresa,
				contentType: "application/json",
				data: JSON.stringify(pedidoModel),
		        cache: false,
		        timeout: 600000,
				success : function(costoEnvio) {
					$("#costo-envio").html('$'+costoEnvio);
				},
				error : function(e) {
					alert("Error!");
				}
			});

        });
        
        /*
        $(".btn-update").on("click",function () {
        	var id = $(this).attr('id');
        	var href = "/api/productos/"+id;
        	
        	$.get(href, function(producto, status) {
        		
        		$('#idProducto').val(producto.idProducto);
                $('#descripcionCorta').val(producto.descripcionCorta);
                $('#descripcionLarga').val(producto.descripcionLarga);
                $('#precio').val(producto.precio);
                $('#precioEnOferta').val(producto.precioEnOferta);
                $('#color').val(producto.color);
                $('#visible').prop("checked", producto.visible);
            });
        	
        	$('#modal-update').modal();
        });
        
        $(".btn-submit").on("click",function () {
        	
        	var productoModel = {};
        	var idProducto = $("#idProducto").val();

            productoModel["descripcionCorta"] = $("#descripcionCorta").val();
            productoModel["descripcionLarga"] = $("#descripcionLarga").val();
            productoModel["precio"] = $("#precio").val();
            productoModel["precioEnOferta"] = $("#precioEnOferta").val();
            productoModel["color"] = $("#color").val();
            productoModel["visible"] = $('#visible').is(':checked');
            
            $.ajax({
				type : "PUT",
				url : "/api/productos/update/"+ idProducto,
				contentType: "application/json",
				data: JSON.stringify(productoModel),
		        cache: false,
		        timeout: 600000,
				success : function(result) {
					window.location.reload();
				},
				error : function(e) {
					alert("Error!");
				}
			});

        });
        
        */
            
});