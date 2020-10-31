USE ecommerce_sports;

/* user1 user  */
INSERT INTO user (id,cantidadcompras,createdat,enabled,password,updatedat,username, first_name,lastname,phone,email) VALUES(1,5,"2020-03-22 00:00:01", 1, "$2y$12$a0iCfDJYp5GE7GSbvsv7j.W/WYVFnlRt9TUebcyzd1k/9N5RkgWlu", "2020-03-22 00:00:01", "user1","franco","aguirre","1111111111","francoaguirre644@gmail.com");
/* user2 user2  */
INSERT INTO user (id,cantidadcompras,createdat,enabled,password,updatedat,username, first_name,lastname,phone,email) VALUES(2,3,"2020-03-22 00:00:01", 1, "$2y$12$W.diP1QnNYq23OutiMKYRuBvqKYQTiVMOuDyuSfnMq5aNGezB6m9W", "2020-03-22 00:00:01", "user2","sergio","avalos","2222222222","SergioAvalos@gmail.com");
/* user3 user2  */
INSERT INTO user (id,cantidadcompras,createdat,enabled,password,updatedat,username, first_name,lastname,phone,email) VALUES(3,4,"2020-03-22 00:00:01", 1, "$2y$12$W.diP1QnNYq23OutiMKYRuBvqKYQTiVMOuDyuSfnMq5aNGezB6m9W", "2020-03-22 00:00:01", "user3","franco","borsani","3333333333","FrancoBorsani@gmail.com");

INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(1, "2020-03-22 00:00:01", "ROLE_USER", "2020-03-22 00:00:01", 1);
INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(2, "2020-03-22 00:00:01", "ROLE_USER", "2020-03-22 00:00:01", 2);
INSERT INTO user_role (id,createdat,role,updatedat,user_id) VALUES(3, "2020-03-22 00:00:01", "ROLE_ADMIN", "2020-03-22 00:00:01", 3);

/*Profiles */
INSERT INTO perfil (id,username,user_role,nombre,apellido,phone,email,imagen,about_me) VALUES(1, "user1", "ROLE_USER","franco","aguirre","1111111111","francoaguirre644@gmail.com",null,"En esta parte podras escribir informacion que te parezca relevante para destacar en la pagina");

INSERT INTO perfil (id,username,user_role,nombre,apellido,phone,email,imagen,about_me) VALUES(2, "user2", "ROLE_USER","sergio","avalos","2222222222","SergioAvalos@gmail.com",null,"En esta parte podras escribir informacion que te parezca relevante para destacar en la pagina");

INSERT INTO perfil (id,username,user_role,nombre,apellido,phone,email,imagen,about_me) VALUES(3, "user3", "ROLE_ADMIN","franco","borsani","3333333333","FrancoBorsani@gmail.com",null,"En esta parte podras escribir informacion que te parezca relevante para destacar en la pagina");


/* Categories */

INSERT INTO categoria(id_categoria,nombre) values (1,"Indumentaria");
INSERT INTO categoria(nombre) values ("Calzado"), ("Categoria3"), ("Categoria4"), ("Categoria5"), ("Categoria6");



/* Brands */

INSERT INTO marca(id_marca,nombre) values (1,"Levi`s");
INSERT INTO marca(nombre) values ("Polo Label"), ("Puma"), ("Agarrate Catalina"), ("Converse"), 
                  ("Unlimit Ride"),("Clon"),("Brooksfield"),("Nike"),("Fila"),("Vizzano"),
                  ("Chelsea Market"),("Destino Collection"),("La Cofradia"),("Salomon");
                  

/* Tags*/

INSERT INTO tag(id_tag, nombre) VALUES (1, "Tag1");
INSERT INTO tag(id_tag, nombre) VALUES (2, "Tag2");
INSERT INTO tag(id_tag, nombre) VALUES (3, "Tag3");
INSERT INTO tag(id_tag, nombre) VALUES (4, "Tag4");
INSERT INTO tag(id_tag, nombre) VALUES (5, "Tag5");
INSERT INTO tag(id_tag, nombre) VALUES (6, "Tag6");
INSERT INTO tag(id_tag, nombre) VALUES (7, "Tag7");
INSERT INTO tag(id_tag, nombre) VALUES (8, "Tag8");


/* Products */

INSERT INTO producto (id_producto,color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones, peso,visible,imagen)
VALUES (1, "Negro", "Levi`s", "Remera Negra Levi`s Batwing", 1, 2899, 2899, "levis_negro_xl_1", "XL", 1, 0, 0, 12, true, "/img/products/Levis1.jpg");
INSERT INTO producto (color,descripcioncorta,descipcionlarga,marca_id_marca,precio,precio_en_oferta,sku,talle,categoria_id_categoria, total_puntaje, cantidad_valoraciones, peso,visible,imagen)
VALUES ("Blanco", "Polo", "Remera Blanca Polo Label", 2, 1299, 879, "polo_blanco_l_2", "L", 1, 0, 0, 3, true, "/img/products/Polo3.jpg"),
       ("Blanco", "Puma", "Remera Blanca Puma Iconic t7 Slim", 3, 2299, 2299, "puma_blanco_m_3", "M", 1, 0, 0, 2, true, "/img/products/Puma1.jpg"),
       ("Negra", "Nike", "Zapatilla Negra Nike AIR MAX BELLA TR 2", 9, 10499, 9449, "nike_negra_39_4", "39", 2, 0, 0, 4, true, "/img/products/nike2.jpg"),
       ("Negro", "Converse", "Buzo Negro Converse All Star Cropped", 5, 3699, 3429, "converse_negro_m_5", "M", 1, 0, 0, 3, true, "/img/products/Converse.jpg"),
       ("Gris", "Chelsea Market", "Pantalon Gris Chelsea Market Strong", 12, 2500, 1697, "chelseamarket_gris_4_6", "4", 1, 0, 0, 4, true, "/img/products/chelsea_market.jpg"),
       ("Azul", "Clon", "Babucha Azul Clon", 7, 4801, 3357, "clon_azul_3_7", "3", 1, 0, 0, 3, true, "/img/products/clon.jpg"),
       ("Azul", "Brooksfield", "Pantalon Azul Brooksfield Kunz", 8, 6487, 4990, "brooksfield_azul_3_8", "3", 1, 0, 0, 2, true, "/img/products/brooksfield.jpg"),
       ("Rosa", "Fila", "Zapatilla Rosa Fila Euro Jogger Femedge", 10, 4990, 4990, "fila_rosa_37_9", "37", 2, 0, 0, 4, true, "/img/products/fila.jpg"),
       ("Blanca", "Vizzano", "Zapatilla Blanca Vizzano", 11, 4399, 4399, "vizzano_blanca_38_10", "38", 2, 0, 0, 3, true, "/img/products/vizzano.jpg");

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

INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,pagado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(1, 40, "Comentario1", "Domicilio1", true, 4000, "Efectivo",1,1);
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,pagado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(2, 40, "Comentario2", "Domicilio2", true, 3000, "Efectivo",2,1);
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,pagado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(3, 40, "Comentario3", "Domicilio3", true, 2000, "Efectivo",2,2);
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,pagado,importeapagar,metodo_pago,carrito_id_carrito,user_id) VALUES(4, 40, "Comentario4", "Domicilio4", true, 1000, "Efectivo",2,1);

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




insert into tarifa_envio(`id_empresa`,`nombre`,de_0_a_05Kg,de_05_a_1Kg,de_1_a_2Kg,de_2_a_3Kg,de_3_a_5Kg,de_5_a_10Kg,de_10_a_15Kg,de_15_a_20Kg,de_20_a_25Kg) values (1,"Andesmar", 295.00, 295.00, 295.00, 317.00, 317.00, 433.00, 540.00, 540.00, 754.00);
insert into tarifa_envio(`id_empresa`,`nombre`,de_0_a_05Kg,de_05_a_1Kg,de_1_a_2Kg,de_2_a_3Kg,de_3_a_5Kg,de_5_a_10Kg,de_10_a_15Kg,de_15_a_20Kg,de_20_a_25Kg) values (2,"Andreani", 367.00, 375.00, 418.00, 460.00, 500.00, 634.00, 1.015, 1.015, 1.015);
insert into tarifa_envio(`id_empresa`,`nombre`,de_0_a_05Kg,de_05_a_1Kg,de_1_a_2Kg,de_2_a_3Kg,de_3_a_5Kg,de_5_a_10Kg,de_10_a_15Kg,de_15_a_20Kg,de_20_a_25Kg) values (3,"OCA", 356.00, 359.00, 363.00, 374.00, 374.00, 393.00, 412.00, 448.00, 493.00);



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

1111	Sporting Goods	Athletics	Soccer			
1112	Sporting Goods	Athletics	Soccer	Soccer Balls		
3973	Sporting Goods	Athletics	Soccer	Soccer Corner Flags		
3141	Sporting Goods	Athletics	Soccer	Soccer Gloves		
6055	Sporting Goods	Athletics	Soccer	Soccer Goal Accessories		
1113	Sporting Goods	Athletics	Soccer	Soccer Goals		
499784	Sporting Goods	Athletics	Soccer	Soccer Protective Gear		
1114	Sporting Goods	Athletics	Soccer	Soccer Protective Gear	Soccer Shin Guards	

1047	Sporting Goods	Athletics	Team Handball			
499785	Sporting Goods	Athletics	Team Handball	Handballs	

1065	Sporting Goods	Athletics	Tennis			
3105	Sporting Goods	Athletics	Tennis	Tennis Ball Hoppers & Carts		
3985	Sporting Goods	Athletics	Tennis	Tennis Ball Machines		
3565	Sporting Goods	Athletics	Tennis	Tennis Ball Savers		
3113	Sporting Goods	Athletics	Tennis	Tennis Balls		
3961	Sporting Goods	Athletics	Tennis	Tennis Nets		
3658	Sporting Goods	Athletics	Tennis	Tennis Racquet Accessories		
3352	Sporting Goods	Athletics	Tennis	Tennis Racquet Accessories	Racquet Vibration Dampeners	
3638	Sporting Goods	Athletics	Tennis	Tennis Racquet Accessories	Tennis Racquet Bags	
3403	Sporting Goods	Athletics	Tennis	Tennis Racquet Accessories	Tennis Racquet Grips & Tape	
3295	Sporting Goods	Athletics	Tennis	Tennis Racquet Accessories	Tennis Racquet Grommets	
3922	Sporting Goods	Athletics	Tennis	Tennis Racquet Accessories	Tennis Racquet String	
3906	Sporting Goods	Athletics	Tennis	Tennis Racquets

1115	Sporting Goods	Athletics	Volleyball			
1117	Sporting Goods	Athletics	Volleyball	Volleyball Nets		
499788	Sporting Goods	Athletics	Volleyball	Volleyball Protective Gear		
499789	Sporting Goods	Athletics	Volleyball	Volleyball Protective Gear	Volleyball Knee Pads	
499787	Sporting Goods	Athletics	Volleyball	Volleyball Training Aids		
1116	Sporting Goods	Athletics	Volleyball	Volleyballs

3276	Sporting Goods	Outdoor Recreation	Skateboarding				
3127	Sporting Goods	Outdoor Recreation	Skateboarding	Skate Rails			
3626	Sporting Goods	Outdoor Recreation	Skateboarding	Skate Ramps			
3670	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboard Parts			
3869	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboard Parts	Skateboard Decks		
505817	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboard Parts	Skateboard Small Parts		
3192	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboard Parts	Skateboard Trucks		
3637	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboard Parts	Skateboard Wheels		
3067	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboarding Protective Gear			
499776	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboarding Protective Gear	Skate Helmets		
7789	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboarding Protective Gear	Skateboarding Gloves		
3488	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboarding Protective Gear	Skateboarding Pads		
1059	Sporting Goods	Outdoor Recreation	Skateboarding	Skateboards			
		
1043	Sporting Goods	Outdoor Recreation	Golf		
8044	Sporting Goods	Outdoor Recreation	Golf	Divot Tools	
7314	Sporting Goods	Outdoor Recreation	Golf	Golf Accessory Sets	
4605	Sporting Goods	Outdoor Recreation	Golf	Golf Bag Accessories	
4537	Sporting Goods	Outdoor Recreation	Golf	Golf Bag Accessories	Golf Bag Carts
4525	Sporting Goods	Outdoor Recreation	Golf	Golf Bag Accessories	Golf Bag Covers & Cases
1044	Sporting Goods	Outdoor Recreation	Golf	Golf Bags	
6864	Sporting Goods	Outdoor Recreation	Golf	Golf Ball Markers	
1045	Sporting Goods	Outdoor Recreation	Golf	Golf Balls	
3642	Sporting Goods	Outdoor Recreation	Golf	Golf Club Parts & Accessories	
4254	Sporting Goods	Outdoor Recreation	Golf	Golf Club Parts & Accessories	Golf Club Grips
4043	Sporting Goods	Outdoor Recreation	Golf	Golf Club Parts & Accessories	Golf Club Headcovers
499780	Sporting Goods	Outdoor Recreation	Golf	Golf Club Parts & Accessories	Golf Club Shafts
1046	Sporting Goods	Outdoor Recreation	Golf	Golf Clubs	
3578	Sporting Goods	Outdoor Recreation	Golf	Golf Flags	
4466	Sporting Goods	Outdoor Recreation	Golf	Golf Gloves	
3106	Sporting Goods	Outdoor Recreation	Golf	Golf Tees	
4467	Sporting Goods	Outdoor Recreation	Golf	Golf Towels	
3772	Sporting Goods	Outdoor Recreation	Golf	Golf Training Aids


*/