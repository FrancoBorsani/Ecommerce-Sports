USE ecommerce_sports;

/* user1 user  */
INSERT INTO user (id,createdat,enabled,password,updatedat,username, first_name,lastname,email) VALUES(1, "2020-03-22 00:00:01", 1, "$2y$12$a0iCfDJYp5GE7GSbvsv7j.W/WYVFnlRt9TUebcyzd1k/9N5RkgWlu", "2020-03-22 00:00:01", "user1","franco","aguirre","francoaguirre644@gmail.com");
/* user2 user2  */
INSERT INTO user (id,createdat,enabled,password,updatedat,username, first_name,lastname,email) VALUES(2, "2020-03-22 00:00:01", 1, "$2y$12$W.diP1QnNYq23OutiMKYRuBvqKYQTiVMOuDyuSfnMq5aNGezB6m9W", "2020-03-22 00:00:01", "user2","sergio","avalos","SergioAvalos@gmail.com");

INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(1, "2020-03-22 00:00:01", "ROLE_USER", "2020-03-22 00:00:01", 1);
INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(2, "2020-03-22 00:00:01", "ROLE_USER", "2020-03-22 00:00:01", 2);

/* Categories */

INSERT INTO categoria(id_categoria,nombre) values (1,"Categoria1");
INSERT INTO categoria(id_categoria,nombre) values (2,"Categoria2");
INSERT INTO categoria(id_categoria,nombre) values (3,"Categoria3");
INSERT INTO categoria(id_categoria,nombre) values (4,"Categoria4");
INSERT INTO categoria(id_categoria,nombre) values (5,"Categoria5");
INSERT INTO categoria(id_categoria,nombre) values (6,"Categoria6");

/* Brands */

INSERT INTO marca(id_marca,nombre) values (1,"Marca1");
INSERT INTO marca(id_marca,nombre) values (2,"Marca2");
INSERT INTO marca(id_marca,nombre) values (3,"Marca3");
INSERT INTO marca(id_marca,nombre) values (4,"Marca4");
INSERT INTO marca(id_marca,nombre) values (5,"Marca5");
INSERT INTO marca(id_marca,nombre) values (6,"Marca6");

/* Products */
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(1, "rojo", "Producto1", "Descripcion Producto1",1,4000,"sku1","talle1",1, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(2, "rojo", "Producto2", "Descripcion Producto1",1,4000,"sku1","talle1",1, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(3, "rojo", "Producto3", "Descripcion Producto1",1,3000,"sku1","talle1",1, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(4, "rojo", "Producto4", "Descripcion Producto1",2,2000,"sku1","talle1",2, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(5, "rojo", "Producto5", "Descripcion Producto1",2,5000,"sku1","talle1",2, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(6, "rojo", "Producto6", "Descripcion Producto1",2,6000,"sku1","talle1",2, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(7, "rojo", "Producto7", "Descripcion Producto1",3,1000,"sku1","talle1",3, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(8, "rojo", "Producto8", "Descripcion Producto1",3,2000,"sku1","talle1",3, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(9, "rojo", "Producto9", "Descripcion Producto1",3,9000,"sku1","talle1",3, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(10, "rojo","Producto10", "Descripcion Producto1",4,4000,"sku1","talle1",4, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(12, "rojo","Producto11", "Descripcion Producto1",4,3000,"sku1","talle1",4, 0, 0);
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones) VALUES(13, "rojo","Producto12", "Descripcion Producto1",4,5000,"sku1","talle1",4, 0, 0);

<<<<<<< Updated upstream
=======
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(1, "rojo", "Producto1", "Descripcion Producto1",1,4000,3600,"sku1","talle1",1, 0, 0,true,"/img/products/man-1.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(2, "rojo", "Producto2", "Descripcion Producto2",1,4000,3000,"sku1","talle1",1, 0, 0,true,"/img/products/man-1.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(3, "rojo", "Producto3", "Descripcion Producto3",1,3000,2600,"sku1","talle1",1, 0, 0,true,"/img/products/man-2.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(4, "rojo", "Producto4", "Descripcion Producto4",2,2000,1600,"sku1","talle1",2, 0, 0,true,"/img/products/man-2.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(5, "rojo", "Producto5", "Descripcion Producto5",2,5000,4300,"sku1","talle1",2, 0, 0,true,"/img/products/man-3.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(6, "rojo", "Producto6", "Descripcion Producto6",2,6000,6000,"sku1","talle1",2, 0, 0,true,"/img/products/man-3.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(7, "rojo", "Producto7", "Descripcion Producto7",3,1000,1000,"sku1","talle1",3, 0, 0,false,"/img/products/women-1.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(8, "rojo", "Producto8", "Descripcion Producto8",3,2000,2000,"sku1","talle1",3, 0, 0,false,"/img/products/women-1.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(9, "rojo", "Producto9", "Descripcion Producto9",3,9000,8500,"sku1","talle1",3, 0, 0,false,"/img/products/women-2.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(10, "rojo","Producto10", "Descripcion Producto10",4,4000,4000,"sku1","talle1",4, 0, 0,false,"/img/products/women-2.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(12, "rojo","Producto11", "Descripcion Producto11",4,3000,3000,"sku1","talle1",4, 0, 0,false,"/img/products/women-3.jpg");
INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones,visible,imagen) VALUES(13, "rojo","Producto12", "Descripcion Producto12",4,5000,5000,"sku1","talle1",4, 0, 0,false,"/img/products/women-3.jpg");



/* carrito*/
INSERT INTO carrito (id_carrito,fecha,total) values 
              (1,CURDATE(),46000),
              (2,'2020-10-03',0);

/* item */
INSERT INTO item (id_item,producto_id_producto,cantidad,id_carrito) values 
	   (1,2,5,1),
	   (2,1,4,1),
       (3,5,2,1);

/* Pedidos */

INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(1, 40, "Comentario1", "Domicilio1", "Pago", 4000, "Efectivo",1,1);
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(2, 40, "Comentario2", "Domicilio2", "Pago", 3000, "Efectivo",2,1);
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(3, 40, "Comentario3", "Domicilio3", "Pago", 2000, "Efectivo",2,2);
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(4, 40, "Comentario4", "Domicilio4", "Pago", 1000, "Efectivo",2,1);

/* Producto - Tag */

INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (1, 1);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (1, 2);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (2, 1);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (3, 3);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (3, 4);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (4, 4);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (4, 5);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (4, 6);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (5, 4);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (6, 4);
INSERT INTO tag_productos (productos_id_producto, tags_id_tag) VALUES (7, 4);

/* Descuentos */

INSERT INTO descuento (id_descuento, codigo, porcentaje) VALUES (1, "", 0);
INSERT INTO descuento (id_descuento, codigo, porcentaje) VALUES (2, "DESCUENTO1", 10);
INSERT INTO descuento (id_descuento, codigo, porcentaje) VALUES (3, "DESCUENTO2", 20);
INSERT INTO descuento (id_descuento, codigo, porcentaje) VALUES (4, "DESCUENTO3", 25);
INSERT INTO descuento (id_descuento, codigo, porcentaje) VALUES (5, "DESCUENTO4", 50);


/*
3598	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	
3191	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Baseball Uniforms
3439	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Basketball Uniforms
3683	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Cheerleading Uniforms
3724	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Cricket Uniforms
3888	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Football Uniforms
3958	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Hockey Uniforms
4003	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Martial Arts Uniforms
3253	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Officiating Uniforms
5564	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Soccer Uniforms
3379	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Softball Uniforms
3852	Apparel & Accessories	Clothing	Uniforms	Sports Uniforms	Wrestling Uniforms

/*

499713	Sporting Goods	Athletics						
1081	Sporting Goods	Athletics	Basketball			
4676	Sporting Goods	Athletics	Basketball	Basketball Hoop Parts & Accessories		
499751	Sporting Goods	Athletics	Basketball	Basketball Training Aids		
1083	Sporting Goods	Athletics	Basketball	Basketballs	

1093	Sporting Goods	Athletics	Football			
3442	Sporting Goods	Athletics	Football	Football Gloves		
3492	Sporting Goods	Athletics	Football	Football Goal Posts		
3656	Sporting Goods	Athletics	Football	Football Kicking Tees & Holders		
1097	Sporting Goods	Athletics	Football	Football Protective Gear			
3998	Sporting Goods	Athletics	Football	Football Training Equipment		
1094	Sporting Goods	Athletics	Football	Footballs

1000	Sporting Goods	Athletics	Gymnastics			
			
1110	Sporting Goods	Athletics	Rugby			
3761	Sporting Goods	Athletics	Rugby	Rugby Balls		
3487	Sporting Goods	Athletics	Rugby	Rugby Gloves		
3881	Sporting Goods	Athletics	Rugby	Rugby Posts		
499782	Sporting Goods	Athletics	Rugby	Rugby Protective Gear
>>>>>>> Stashed changes







