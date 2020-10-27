$(document).ready(function () {
	
        $(".visible").on("click",function () {
        	var id = $(this).attr('id');
			
        	/********************************************
        	$.ajax({
				type : "GET",
				url : "/get",
				success : function(result) {
					alert(result);
				},
				error : function(e) {
					alert("ERROR!");
				}
			});
			
			********************************************/

				$.ajax({
					type : "PATCH",
					url : "/api/productos/visibility/"+id,
					success : function(result) {
						$("#message").html(result);
						$('#alert-visibility').modal();
					},
					error : function(e) {
						alert("Error!");
					}
				});

        });
        
        
        $(".btn-update").on("click",function () {
        	var id = $(this).attr('id');
        	var href = "/api/productos/"+id;
        	
        	$.get(href, function(producto, status) {
        		
        		$('#idProducto').val(producto.idProducto);
                $('#descripcionCorta').val(producto.descripcionCorta);
                $('#descripcionLarga').val(producto.descripcionLarga);
                $('#precio').val(producto.precio);
                $('#precioEnOferta').val(producto.precioEnOferta);
                $("#categoria").val(producto.categoria.nombre).change();
                $("#marca").val(producto.marca.nombre).change();
                $('#color').val(producto.color);
                $('#visible').prop("checked", producto.visible);
                
            });
        	
        	$('#modal-update').modal();
        });
        
        $(".btn-submit").on("click",function () {
        	
        	var productoModel = {};
        	var idProducto = $("#idProducto").val();

        	var productoModel = {
          		  "descripcionCorta": $("#descripcionCorta").val(),
          		  "descripcionLarga": $("#descripcionLarga").val(),
          		  "precio": $("#precio").val(),
          		  "precioEnOferta": $("#precioEnOferta").val(),
          		  "color": $("#color").val(),
          		  "marca": {
          		    "nombre": $("#marca").val(),
          		  },
          		  "categoria": {
            		    "nombre": $("#categoria").val(),
            		  }
          		 }
            
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
        
        
$(".btn-submit-add").on("click",function () {
        	         
            var productoModel = {
            		  "descripcionCorta": $("#descripcionCorta-add").val(),
            		  "descripcionLarga": $("#descripcionLarga-add").val(),
            		  "precio": $("#precio-add").val(),
            		  "precioEnOferta": $("#precioEnOferta-add").val(),
            		  "imagen": $("#imagen-add").val(),
            		  "color": $("#color-add").val(),
            		  "marca": {
            		    "nombre": $("#marca-add").val(),
            		  },
            		  "categoria": {
              		    "nombre": $("#categoria-add").val(),
              		  }
            		 }
            
            $.ajax({
				type : "POST",
				url : "/api/productos/create",
				contentType: "application/json",
				data: JSON.stringify(productoModel),
		        cache: false,
		        timeout: 600000,
				success : function(result) {
					
					if(!result){
						alert("Producto agregado correctamente");						
					}else{
						alert("El producto que usted quiere registrar ya existe");
					}	
					
					window.location.reload();
					
				},
				error : function(e) {
					alert("Error!");
				}
			});

        });
        
        
        $(".btn-add").on("click",function () {        
        	$('#modal-add').modal();
        });
        
       
        
        $('#myTable').dataTable({
	        "oLanguage": {
	            "sLengthMenu": "Mostrar _MENU_ registros",
	            "sZeroRecords": "No se encontraron registros ",
	            "oPaginate": {
	                "sNext": "Siguiente",
	                "sPrevious": "Anterior"
	            },

	        },
	        "iDisplayLength": 5,
	        "aLengthMenu": [
	            [5, 10, 25, -1],
	            [5, 10, 25, "All"]
	        ],

	        "bInfo": false,
	        "bLengthChange":false
	    });
            
});