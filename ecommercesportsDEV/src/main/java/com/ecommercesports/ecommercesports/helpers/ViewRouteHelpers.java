package com.ecommercesports.ecommercesports.helpers;

public class ViewRouteHelpers {
	
	//Home
	public final static String HOME = "home/index";
	public final static String CONTACTO = "contact/index";
	public final static String INFO = "institutional_info/index";

	//Carrito
	public final static String CARRITO_INDEX = "carrito/index";
	public final static String CARRITO_ROOT = "/carritos";
	
	//Producto
    public final static String PRODUCTO_INDEX = "producto/index";
    public final static String PRODUCTO_ROOT = "productos";
    public final static String PRODUCTO_SELECCIONADO = "producto/producto";
    public final static String PRODUCTO_DEST_DPT_LF = "producto/destacadosLF";
    public final static String PRODUCTO_DEST_DPT_G = "producto/destacadosG";
    public final static String PRODUCTO_NOT_FOUND = "producto/notFound";
    public final static String PRODUCTO_GESTOR = "producto/gestor";
    public final static String PRODUCTO_OFERTA= "producto/ofertas";
    
	//Pedido
	public static final String PEDIDO_INDEX = "pedido/index";
	public static final String PEDIDO_DETALLE ="pedido/detallePedido";
    public final static String PEDIDO_ROOT = "/pedidos";
    
    // Checkout
    
    public static final String CHECKOUT_INDEX = "checkout/index";
    public static final String ENVIO = "checkout/envio";
    public static final String FORM_DOMICILIO = "checkout/formAdress";
    public static final String PAGO = "checkout/pago";
    public final static String CHECKOUT_ROOT = "/checkout";
    
    //Acceso
    
    public final static String USER_LOGIN = "acceso/ingreso";
    public final static String USER_REGISTRO = "acceso/registro";
    public final static String USER_RECUPERARCLAVE = "acceso/recuperarclave";
    public final static String USER_VERIFICARCLAVE = "acceso/ingresoTemporal";
    public final static String USER_CAMBIARCLAVE = "profile/cambiarclave";
    public final static String USER_UPDATE_USER = "profile/editarUsuario";
    
    
    //redirects	
  	public final static String ROUTE_INDEX = "/";
    
    //Perfil
    public final static String PROFILE_INDEX = "profile/index";

  	
}
