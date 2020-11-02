  $(document).ready(function () {
      
      $("#search").keyup(function () {
          $("#result").html("");
          $("#state").val("");
          const searchField = $("#search").val();
          const expression = new RegExp(searchField, "i");

          $.getJSON("/productos/getAllProducts", function (data) {
            var i = 0;
            $.each(data, function (key, value) {
              if (
                value.descripcionCorta.search(expression) != -1 && i<4
              ) {
                  
                $("#result").append(
                  '<a href="' + "/productos/" + value.idProducto +'"><li class="list-group-item link-class"><img src="' +
                  value.imagen +
                    '" height="40" width="40" class="img-thumbnail" /> ' +
                    value.descripcionCorta + " | " + value.descripcionLarga + " | $"  + ( (value.precio != value.precioEnOferta) ? value.precioEnOferta : value.precio ) +   
                    "</li></a>"
                );
                i++;
              }
            });
            if (searchField == "") $("#result").html("");
          });
        });

        $("#result").on("click", "li", function () {
          var click_text = $(this).text().split("|");
          $("#search").val($.trim(click_text[0]));
          $("#result").html("");
        });
        
        $("#result")
        .focusout(function() {
            $("#result").html("");
        })
        
        $("body").on("click", function () {
          $("#result").html("");
        });
        
        $( "#form-search" ).submit(function(e) {
              e.preventDefault();
              const searchField = $("#search").val();
              if(searchField != "") window.location.href = "/productos/search="+searchField;
        });
        
    
  });