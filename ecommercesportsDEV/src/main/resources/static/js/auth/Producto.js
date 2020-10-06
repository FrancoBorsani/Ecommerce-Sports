$(document).ready(function () {
	
        $(".visible").on("click",function () {
        	const id = $(this).attr('id');
			
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
            
});