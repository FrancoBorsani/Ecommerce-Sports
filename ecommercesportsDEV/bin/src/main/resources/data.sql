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

/* Pedidos */

INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago) VALUES(1, 40, "Comentario1", "Domicilio1", "Pago", 4000, "Efectivo" );
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago) VALUES(2, 40, "Comentario2", "Domicilio2", "Pago", 3000, "Efectivo" );
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago) VALUES(3, 40, "Comentario3", "Domicilio3", "Pago", 2000, "Efectivo" );
INSERT INTO pedido (id_pedido,cantidad,comentario,domicilio,estado,importeapagar,metodo_pago) VALUES(4, 40, "Comentario4", "Domicilio4", "Pago", 1000, "Efectivo" );

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