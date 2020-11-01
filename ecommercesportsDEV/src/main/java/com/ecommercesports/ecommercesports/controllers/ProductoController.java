package com.ecommercesports.ecommercesports.controllers;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ecommercesports.ecommercesports.converters.CarritoConverter;
import com.ecommercesports.ecommercesports.converters.ProductoConverter;
import com.ecommercesports.ecommercesports.converters.ValoracionConverter;
import com.ecommercesports.ecommercesports.entities.Categoria;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.entities.Pedido;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.entities.User;
import com.ecommercesports.ecommercesports.entities.Valoracion;
import com.ecommercesports.ecommercesports.helpers.ViewRouteHelpers;
import com.ecommercesports.ecommercesports.models.ComentarioModel;
import com.ecommercesports.ecommercesports.models.RegistroExcelModel;
import com.ecommercesports.ecommercesports.repositories.IComentarioRepository;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.repositories.IPedidoRepository;
import com.ecommercesports.ecommercesports.repositories.IProductoRepository;
import com.ecommercesports.ecommercesports.repositories.IUserRepository;
import com.ecommercesports.ecommercesports.repositories.IValoracionRepository;
import com.ecommercesports.ecommercesports.services.ICarritoService;
import com.ecommercesports.ecommercesports.services.ICategoriaService;
import com.ecommercesports.ecommercesports.services.IComentarioService;
import com.ecommercesports.ecommercesports.services.IItemService;
import com.ecommercesports.ecommercesports.services.IMarcaService;
import com.ecommercesports.ecommercesports.services.IPedidoService;
import com.ecommercesports.ecommercesports.services.IPerfilService;
import com.ecommercesports.ecommercesports.services.IProductoService;
import com.ecommercesports.ecommercesports.services.IUserLogueadoService;
import com.ecommercesports.ecommercesports.services.IValoracionService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;
    
    @Autowired
    @Qualifier("carritoConverter")
    private CarritoConverter carritoConverter;
    
    @Autowired
    @Qualifier("carritoService")
    private ICarritoService carritoService;
    
    @Autowired
    @Qualifier("categoriaService")
    private ICategoriaService categoriaService;
    
    @Autowired
    @Qualifier("marcaService")
    private IMarcaService marcaService;
    
    @Autowired
    @Qualifier("valoracionService")
    private IValoracionService valoracionService;
    
    @Autowired
    @Qualifier("comentarioService")
    private IComentarioService comentarioService;
    
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("itemService")
	private IItemService itemService;
	
	@Autowired
	@Qualifier("valoracionRepository")
	private IValoracionRepository valoracionRepository;
	
	@Autowired
	@Qualifier("itemRepository")
	private IItemRepository itemRepository;
	
	@Autowired
	@Qualifier("comentarioRepository")
	private IComentarioRepository comentarioRepository;
	
   @Autowired
    @Qualifier("productoRepository")
    private IProductoRepository productoRepository;

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;
	
    @Autowired
	@Qualifier("pedidoService")
	private IPedidoService pedidoService;
    
    @Autowired
	@Qualifier("pedidoRepository")
	private IPedidoRepository pedidoRepository;
    
    @Autowired
	@Qualifier("userLogueadoService")
	private IUserLogueadoService userLogueadoService;
    
	@Autowired
	@Qualifier("perfilService")
	private IPerfilService perfilService;
	
	@Autowired
	@Qualifier("valoracionConverter")
	private ValoracionConverter valoracionConverter;
	
    @GetMapping({"", "/_DisplayType_LF"})
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        mAV.addObject("productos", productoService.getAllProductosVisibles());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    /****************************************************************************************/
    @GetMapping("/categorias/{id}/_DisplayType_G")
    public ModelAndView categoryCards(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        List<Producto> productos = null;
        List<Producto> ofertas = null;
        Categoria categoriaFijada = null;
        
        if(!productoService.findByCategoria(categoria).isEmpty()) {
        	
        	productos = productoService.findByCategoria(categoria);
        	categoriaFijada = productoService.findByCategoria(categoria).get(0).getCategoria();
        }
        if(!productoService.getProductosEnOferta().isEmpty()) ofertas = productoService.getProductosEnOferta();
        		
        mAV.addObject("productos", productos);
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", ofertas);
        mAV.addObject("categoria", categoriaFijada);
        
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    /****************************************************************************************/
    
    @GetMapping("/marcas/{id}/_DisplayType_G")
    public ModelAndView brandsCards(@PathVariable("id") String marca) {
        ModelAndView mAV = new ModelAndView("producto/cards");
        mAV.addObject("productos", productoService.filterByMarca(marca));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/_DisplayType_G")
    public ModelAndView cards() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        mAV.addObject("productos", productoService.getAllProductosVisibles());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") long idProducto) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_SELECCIONADO);
        mAV.addObject("producto", productoService.findByIdProducto(idProducto));
        mAV.addObject("comentarios", comentarioRepository.findByIdProducto(idProducto));
        mAV.addObject("relacionados", productoService.getRelated(idProducto));
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        mAV.addObject("valoracion", valoracionRepository.findVByIdProducto(idProducto));
        
        String username = "";
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if( principal instanceof UserDetails) {
    		username = ((UserDetails)principal).getUsername();
    	}
    	User currentUser = userRepository.findByUsername(username);
    	
    	
    	List<Pedido> pedidos = new ArrayList<Pedido>();
        List<Item> listaProductos = new ArrayList<Item>();
    	Pedido p = new Pedido();
   
    	boolean encontrado = false;
    	boolean puedeValorar = false;
   
    	try {
    		if(currentUser != null) {
    		if(currentUser.getCantidadcompras() != 0) {
    		pedidos = pedidoRepository.traerPedidosDelUser(currentUser.getId());
    		for(Pedido ped: pedidos){
    			for (Item item : itemRepository.itemsDelCarrito(ped.getCarrito().getIdCarrito())){
    			    			listaProductos.add(item);
    			    		}}
    		for(Item item : listaProductos) {
    			if(item.getProducto().getIdProducto() == idProducto) {
    				encontrado = true;
    				mAV.addObject("encontrado", encontrado);
    				puedeValorar = true;
    				for(Valoracion v : valoracionRepository.obtenerValoraciones()) {
    				if(v.getUser().getId()== currentUser.getId() && v.getProducto().getIdProducto() == idProducto) {		
    						puedeValorar = false;	
    						mAV.addObject("puedeValorar", puedeValorar);
    					}
    				}
    			
    				
    			}

    		
    	}}
    		if(puedeValorar)
			{        				
				mAV.addObject("puedeValorar", puedeValorar);
			}
    		
    		
    		}
    		
		} catch (Exception e) {
			mAV.addObject("pedido", p);
    		mAV.addObject("items", listaProductos);
		}
    	finally { 
    		return mAV; 
    	}
        
        
    
    }
    

    /****************************************************************************************/
    @GetMapping("/categorias/{id}")
    public ModelAndView getByCategoria(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        List<Producto> productos = null;
        List<Producto> ofertas = null;
        Categoria categoriaFijada = null;
        
        if(!productoService.findByCategoria(categoria).isEmpty()) {
        	
        	productos = productoService.findByCategoria(categoria);
        	categoriaFijada = productoService.findByCategoria(categoria).get(0).getCategoria();
        }
        if(!productoService.getProductosEnOferta().isEmpty()) ofertas = productoService.getProductosEnOferta();
        		
        mAV.addObject("productos", productos);
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", ofertas);
        mAV.addObject("categoria", categoriaFijada);
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    /****************************************************************************************/
    
    @GetMapping("/categorias/{id}_DisplayType_LF")
    public ModelAndView getByCategoriaDisplayType_LF(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.findByCategoria(categoria));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        mAV.addObject("categoria", productoService.findByCategoria(categoria).get(0).getCategoria());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/categorias/{id}_DisplayType_G")
    public ModelAndView getByCategoriaDisplayType_G(@PathVariable("id") String categoria) {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.findByCategoria(categoria));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        mAV.addObject("categoria", productoService.findByCategoria(categoria).get(0).getCategoria());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/marcas/{id}")
    public ModelAndView getByMarca(@PathVariable("id") String marca) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.filterByMarca(marca));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/getAllProducts")
	@ResponseBody
	public List<Producto> getAllProducts(){
		return productoService.getAll();
	}
    
    @GetMapping("/search={id}")
    public ModelAndView search(@PathVariable("id") String keyword,Model model) {
    	
    	if (productoService.searchProduct(keyword).size() == 0) { 
    		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_NOT_FOUND);
    		model.addAttribute("searchTerm", keyword);
    		
    		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
            	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
            }
    		
    		return mAV;
    	}else {
    		ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
            
            mAV.addObject("productos",productoService.searchProduct(keyword));
            mAV.addObject("categorias", categoriaService.getAll());
            mAV.addObject("marcas", marcaService.getAll());
            model.addAttribute("searchTerm", keyword);
            mAV.addObject("ofertas", productoService.getProductosEnOferta());
            
            if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
            	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
            }
            
            return mAV;
    	}
    	
    }
    
    @GetMapping("/search={id}_DisplayType_G")
    public ModelAndView searchDisplayType_G(@PathVariable("id") String keyword) {
    	
    	if (productoService.searchProduct(keyword).size() == 0) { 
    		ModelAndView mAV = new ModelAndView("producto/cards");
    		
    		if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
            	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
            }
    		
    		return new ModelAndView("producto/notFound");
    		
    	}else {
    		ModelAndView mAV = new ModelAndView("producto/cards");
            
            mAV.addObject("productos",productoService.searchProduct(keyword));
            mAV.addObject("categorias", categoriaService.getAll());
            mAV.addObject("marcas", marcaService.getAll());
            mAV.addObject("ofertas", productoService.getProductosEnOferta());
            
            if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
            	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
            }
            
            return mAV;
    	}
    	
    }
    
    @GetMapping("/_OrderId_PRICE*DESC")
    public ModelAndView orderByPriceDesc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByPriceDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_OrderId_PRICE*ASC")
    public ModelAndView orderByPriceAsc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByPriceAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_OrderId_NAME*DESC")
    public ModelAndView orderByNameDesc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByNameDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_OrderId_NAME*ASC")
    public ModelAndView orderByNameAsc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_INDEX);
        
        mAV.addObject("productos",productoService.orderByNameAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_PRICE*DESC")
    public ModelAndView orderByPriceDescDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByPriceDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_PRICE*ASC")
    public ModelAndView orderByPriceAscDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByPriceAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_NAME*DESC")
    public ModelAndView orderByNameDescDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        mAV.addObject("productos",productoService.orderByNameDesc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }

        return mAV;
    }
    
    @GetMapping("/_DisplayType_G_OrderId_NAME*ASC")
    public ModelAndView orderByNameAscDisplay_G() {
        ModelAndView mAV = new ModelAndView("producto/cards");
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        mAV.addObject("productos",productoService.orderByNameAsc());
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());

        return mAV;
    }

    @PostMapping("/back")
    public RedirectView back() {
        return new RedirectView(ViewRouteHelpers.PRODUCTO_ROOT);
    }
    
/************************************************************************************************/
    @GetMapping("/destacados_DPT_LF_OrderId_PRICE*ASC")
    public ModelAndView destacados_DisplayType_LF_orderByPriceAsc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
        mAV.addObject("productos", productoService.productosDestacados("orderByPriceAsc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_LF_OrderId_PRICE*DESC")
    public ModelAndView destacados_DisplayType_LF_orderByPriceDesc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
        mAV.addObject("productos", productoService.productosDestacados("orderByPriceDesc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_LF_OrderId_NAME*ASC")
    public ModelAndView destacados_DisplayType_LF_orderByNameAsc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
        mAV.addObject("productos", productoService.productosDestacados("orderByNameAsc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_LF_OrderId_NAME*DESC")
    public ModelAndView destacados_DisplayType_LF_orderByNameDesc() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
        mAV.addObject("productos", productoService.productosDestacados("orderByNameDesc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
/***********************************************************************************************/
    

    /************************************************************************************************/
    @GetMapping("/destacados_DPT_G_OrderId_PRICE*ASC")
    public ModelAndView destacados_DisplayType_G_orderByPriceAsc() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_G);
        mAV.addObject("productos", productoService.productosDestacados("orderByPriceAsc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_G_OrderId_PRICE*DESC")
    public ModelAndView destacados_DisplayType_G_orderByPriceDesc() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_G);
        mAV.addObject("productos", productoService.productosDestacados("orderByPriceDesc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_G_OrderId_NAME*ASC")
    public ModelAndView destacados_DisplayType_G_orderByNameAsc() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_G);
        mAV.addObject("productos", productoService.productosDestacados("orderByNameAsc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_G_OrderId_NAME*DESC")
    public ModelAndView destacados_DisplayType_G_orderByNameDesc() {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_G);
        mAV.addObject("productos", productoService.productosDestacados("orderByNameDesc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
                
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
/***********************************************************************************************/
    
    @GetMapping("/destacados_DPT_LF")
    public ModelAndView destacados_DisplayType_LF() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
        mAV.addObject("productos", productoService.productosDestacados("orderByPriceAsc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/destacados_DPT_G")
    public ModelAndView destacados_DisplayType_G() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_G);
        mAV.addObject("productos", productoService.productosDestacados("orderByPriceAsc"));
        mAV.addObject("categorias", categoriaService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("ofertas", productoService.getProductosEnOferta());
        
        for(Producto p : productoService.productosDestacados("orderByPriceAsc")) {
        	System.out.println(p);
        }
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    } 
    
    
    @PostMapping("/agregarComentario")
    public ModelAndView agregarComentario(@RequestParam("comentario") String comentario, @RequestParam("id") String id) {
    	 ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_DEST_DPT_LF);
    	 mAV.addObject("productos", productoService.productosDestacados("orderByPriceAsc"));
         mAV.addObject("categorias", categoriaService.getAll());
         mAV.addObject("marcas", marcaService.getAll()); 
         mAV.addObject("ofertas", productoService.getProductosEnOferta());
         
         if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
         	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
         }

    	ComentarioModel comentarioNuevo = new ComentarioModel();
    	comentarioNuevo.setComentario(comentario);
    	
    	
    	String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		}    	
    	
		User u = userRepository.findByUsername(username);
		comentarioNuevo.setUser(u);		
		comentarioNuevo.setProducto(productoRepository.findByIdProducto(Long.parseLong(id)));
		
		comentarioService.insertOrUpdate(comentarioNuevo);
    	
		
    	return mAV;
    }
    

    @PostMapping("/valorar")
    public ModelAndView valorar(@RequestParam("puntaje") int puntaje, @RequestParam("id") String id) {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PUNTUACIONDADA);
    	 mAV.addObject("productos", productoService.productosDestacados("orderByPriceAsc"));
         mAV.addObject("categorias", categoriaService.getAll());
         mAV.addObject("marcas", marcaService.getAll()); 
         
         if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
         	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
         }
                 
    	System.out.println("EL PUNTAJE QUE LLEGA: "+ puntaje);
    	//ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PUNTUACIONDADA);         
   
    	Producto p = productoRepository.findByIdProducto(Long.parseLong(id));

    	
    	  String username = "";
      	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      	if( principal instanceof UserDetails) {
      		username = ((UserDetails)principal).getUsername();
      	}
      	
      	User currentUser = userRepository.findByUsername(username);	
      	Valoracion v = new Valoracion();
      	v.setUser(currentUser);
      	v.setProducto(p);
      	v.setCantidadValoraciones(p.getCantidadValoraciones() + 1);
      	v.setTotalPuntaje(puntaje);
      	valoracionService.insertOrUpdate(valoracionConverter.entityToModel(v));
  
    	return mAV;
    }
    
    @GetMapping("/admin_productos")
    public ModelAndView gestor() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_GESTOR);
        mAV.addObject("productos", productoService.getAll());
        mAV.addObject("marcas", marcaService.getAll());
        mAV.addObject("categorias", categoriaService.getAll());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        return mAV;
    }
    
    @GetMapping("/ofertas")
    public ModelAndView ofertas() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTO_OFERTA);
        mAV.addObject("productos", productoService.getProductosEnOferta());
        
        if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
        
        for(Producto p : productoService.getProductosEnOferta()) {
        	System.out.println(p);
        }
        
        return mAV;
    }
  
    
    @GetMapping("/importarDesdeExcel")
    public ModelAndView uploadFile()
    {
    	ModelAndView mAV = new ModelAndView(ViewRouteHelpers.PRODUCTOS_GUARDADOS);
    	
    	if(userLogueadoService.traerUserLogueado() != null && carritoService.carritoDelUserLogueadoParaController() != null) {
        	mAV.addObject("carrito", carritoService.carritoDelUserLogueadoParaController());
        }
    
    	List<RegistroExcelModel> listaRegistrosExcel = productoService.traerRegistrosEnlistaModel();
		List<Producto> productosCargados = new ArrayList<Producto>();
		List<Producto> productos_NO_Cargados = new ArrayList<Producto>();
		for(RegistroExcelModel rE : listaRegistrosExcel) {//paso de registroExcel a producto cargo en BD (este paso es necesario, solo porque productos tiene clase Categori y Marca, de lo contrario sería más directo y no haría falta la clase "RegistroExcelModel", solo bastaría con agregar @ExcelCellName("...") a Producto (Entity) )                   	
			if(!productoService.hayCamposEnNULL(rE)) {
				Producto producto = productoService.cargaParcialDeProducto(rE);
				if(productoService.traerSiExisteElProductoEnBD(producto, rE.getCategoria(), rE.getMarca())== null) {//Solo se va a cargar si no hay uno con los mismos valores
					productosCargados.add(productoService.cagarProductoEnBDConMarcaYCategoriaYtraer(producto, rE.getCategoria(), rE.getMarca()));
				}else {
					productos_NO_Cargados.add(producto);
				}//Fin else del if traerExisteElProductoEnBD				
			}// Fin if hayCamposEnNULL(rE)
		}//Fin for listaRegistrosExcel)
    	
		mAV.addObject("productos_NO_Cargados", productos_NO_Cargados);
    	mAV.addObject("productosCargados", productosCargados);
    	
    	
        return mAV;
    }    
    
    @GetMapping("/exportadorFormatoFacebook")
	public ResponseEntity<InputStreamResource> exportAllData() throws Exception {
		ByteArrayInputStream stream = productoService.exportadorFormatoFacebook();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=productos.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
    
    
}//Fin class