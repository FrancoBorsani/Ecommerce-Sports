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
                $('#color').val(producto.color);
                $('#visible').prop("checked", producto.visible);
            });
        	
        	$('#modal-update').modal();
        });
        
        $(".btn-submit").on("click",function () {
        	
        	var productoModel = {};

            productoModel["idProducto"] = $("#idProducto").val();
            productoModel["descripcionCorta"] = $("#descripcionCorta").val();
            productoModel["descripcionLarga"] = $("#descripcionLarga").val();
            productoModel["precio"] = $("#precio").val();
            productoModel["precioEnOferta"] = $("#precioEnOferta").val();
            productoModel["color"] = $("#color").val();
            productoModel["visible"] = $('#visible').is(':checked');
            
            $.ajax({
				type : "PUT",
				url : "/api/productos/update/"+1,
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