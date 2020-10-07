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
        		
                $('#descripcionCorta').val(producto.descripcionCorta);
                $('#descripcionLarga').val(producto.descripcionLarga);
                $('#precio').val(producto.precio);
                $('#color').val(producto.color);
                $('#visible').prop("checked", producto.visible);
               
            });
        	
        	$('#modal-update').modal();
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