package com.ecommercesports.ecommercesports.implementation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.ProductoConverter;
import com.ecommercesports.ecommercesports.entities.Producto;
import com.ecommercesports.ecommercesports.entities.Tag;
import com.ecommercesports.ecommercesports.models.ProductoModel;
import com.ecommercesports.ecommercesports.models.RegistroExcelModel;
import com.ecommercesports.ecommercesports.repositories.IProductoRepository;
import com.ecommercesports.ecommercesports.services.ICategoriaService;
import com.ecommercesports.ecommercesports.services.IMarcaService;
import com.ecommercesports.ecommercesports.services.IProductoService;
import com.poiji.bind.Poiji;



@Service("productoService")
public class ProductoService implements IProductoService {

    @Autowired
    @Qualifier("productoRepository")
    private IProductoRepository productoRepository;

    @Autowired
    @Qualifier("productoConverter")
    private ProductoConverter productoConverter;
    
	@Autowired
	@Qualifier("marcaService")
	private IMarcaService marcaService;

	@Autowired
	@Qualifier("categoriaService")
	private ICategoriaService categoriaService;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoModel findByIdProducto(long idProducto) {
        return productoConverter.entityToModel(productoRepository.findByIdProducto(idProducto));
    }

    @Override
    public ProductoModel insertOrUpdate(ProductoModel productoModel) {
        Producto producto = productoRepository.save(productoConverter.modelToEntity(productoModel));
        return productoConverter.entityToModel(producto);
    }
    
    @Override
    public List<Producto> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    @Override
    public boolean remove(long idProducto) {
        try {
            productoRepository.deleteById(idProducto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	@Override
	public List<Producto> filterByMarca(String marca) {
		// TODO Auto-generated method stub
		return productoRepository.filterByMarca(marca);
	}

	@Override
	public List<Producto> productosDestacados(String order_filter) {
		List<Producto> destacados = new ArrayList<Producto>();
		List<Producto> productosAIterar = new ArrayList<Producto>();
		
		switch (order_filter) {
		case "orderByPriceAsc":
			productosAIterar = productoRepository.orderByPriceAsc();
			break;
		case "orderByPriceDesc":
			productosAIterar = productoRepository.orderByPriceDesc();
			break;
		case "orderByNameAsc":
			productosAIterar = productoRepository.orderByNameAsc();
			break;
		case "orderByNameDesc":
			productosAIterar = productoRepository.orderByNameDesc();
			break;

		default:
			productosAIterar = productoRepository.orderByPriceAsc();
			break;
		}
		
        for(Producto p : productosAIterar) {
        	if(p.getIdProducto() == 1 || (p.getIdProducto()-1)%3 == 0){
              destacados.add(p);        		
        	}
        }
		return destacados;
	}
	
	@Override
	public List<Producto> searchProduct(String keyword) {
		// TODO Auto-generated method stub
		return productoRepository.searchProduct(keyword);
	}

	@Override
	public List<Producto> orderByPriceAsc() {
		// TODO Auto-generated method stub
		return productoRepository.orderByPriceAsc();
	}

	@Override
	public List<Producto> orderByPriceDesc() {
		// TODO Auto-generated method stub
		return productoRepository.orderByPriceDesc();
	}

	@Override
	public List<Producto> orderByNameAsc() {
		// TODO Auto-generated method stub
		return productoRepository.orderByNameAsc();
	}

	@Override
	public List<Producto> orderByNameDesc() {
		// TODO Auto-generated method stub
		return productoRepository.orderByNameDesc();
	}

    @Override
    public List<Producto> getRelated(long idProducto) {
        List<Producto> relacionados = new ArrayList<>();
        Producto producto = productoRepository.findByIdProducto(idProducto);

        for (Tag tag: producto.getTags()) {
            for (Producto pRel: productoRepository.getRelated(tag.getId())) {
                if (pRel.getIdProducto() != idProducto && pRel.isVisible()) {
                    relacionados.add(pRel);
                }
            }
        }

        relacionados = relacionados.stream().distinct().collect(Collectors.toList());

        return relacionados;
    }

	@Override
	public void changeVisible(boolean visible, long idProducto) {
		// TODO Auto-generated method stub
		productoRepository.changeVisible(visible, idProducto);
	}

	@Override
	public List<Producto> getProductosEnOferta() {
		// TODO Auto-generated method stub
		return productoRepository.getProductosEnOferta();
	}

	@Override
	public void updateProducto(String descripcionCorta, String descripcionLarga, double precio,
			double precioEnOferta, String color, boolean visible, long idProducto) {
		// TODO Auto-generated method stub
		productoRepository.updateProducto(descripcionCorta, descripcionLarga, precio, precioEnOferta, color, visible, idProducto);
	}


	@Override
	public List<RegistroExcelModel> traerRegistrosEnlistaModel(){
		File file = new File("src/main/resources/ArchivoExcel/productos.xlsx");
		List<RegistroExcelModel> listaRegistrosExcel = Poiji.fromExcel(file, RegistroExcelModel.class);
		return listaRegistrosExcel;
	}
	
	@Override
	public Producto cagarProductoEnBDConMarcaYCategoriaYtraer(Producto producto, String nombreCategoria, String nombreMarca) {
		producto.setCategoria(categoriaService.traerCategoriaPorNombreO_Crear(nombreCategoria));//si NO existe el producto pueden o no existir la marca y la categoría
		producto.setMarca(marcaService.traerMarcaPorNombreO_Crear(nombreMarca));
		productoRepository.save(producto);//si llega a está función es porque no tiene impedimentos para ser guardado
		return productoRepository.findAll().get(productoRepository.findAll().size()-1);  
	}

	@Override
	public Producto cargaParcialDeProducto(RegistroExcelModel rE) {
		Producto producto = new Producto();
		producto.setPrecio(rE.getPrecio());
		producto.setPrecioEnOferta(rE.getPrecioEnOferta());
		producto.setDescripcionCorta(rE.getDescripcionCorta());
		producto.setDescripcionLarga(rE.getDescripcionLarga());
		producto.setSku(rE.getSku());
		producto.setTalle(rE.getTalle());
		producto.setColor(rE.getColor());
		producto.setPeso(rE.getPeso());
		producto.setVisible(rE.isVisible());
		producto.setTotalPuntaje_2(rE.getTotalPuntaje());
		producto.setCantidadValoraciones(rE.getCantidadValoraciones());
		producto.setImagen(rE.getImagen());
		return producto;
	}
	
	@Override
	public boolean hayCamposEnNULL(RegistroExcelModel rE) {
		boolean tieneCampoNulo = false;
		if(rE.getDescripcionCorta()== null || rE.getDescripcionLarga()== null || rE.getSku()==null || 
				rE.getTalle()== null || (rE.isVisible()!=true && rE.isVisible()!= false) || rE.getImagen() == null){
			tieneCampoNulo = true;
		}

		return tieneCampoNulo;
	} 

	@Override
	public Producto traerSiExisteElProductoEnBD(Producto producto, String nombreCategoria, String nombreMarca) {
		Producto productoAux = null;
		try {
			producto.setCategoria(categoriaService.traerCategoriaPorNombre(nombreCategoria));//para que exista el producto debe existir la marca y la categoría
			producto.setMarca(marcaService.traerMarcaPorNombre(nombreMarca));
			productoAux = productoRepository.traerPorVariosAtributos(producto.getColor(), producto.getDescripcionCorta(), producto.getDescripcionLarga(), producto.getImagen(), producto.getSku(), producto.getTalle(), producto.getCategoria().getIdCategoria(), producto.getMarca().getIdMarca());
			return productoAux;
		} catch (Exception e) {
			return productoAux;
		}
	}

	@Override
	public List<Producto> getAllProductosVisibles() {
		// TODO Auto-generated method stub
		return productoRepository.getAllProductosVisibles();
	}

	@Override
	public List<Producto> getProductosSinOferta() {
		// TODO Auto-generated method stub
		return productoRepository.getProductosSinOferta();
	}
	
	@Override
	public ByteArrayInputStream exportadorFormatoFacebook() throws Exception {
		String[] columns = { "Id", "title", "description", "availability", "condition", "price", "link", "image_link" , "brand" };

		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		Sheet sheet = workbook.createSheet("Productos");
		Row row = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}

		List<Producto> productos = this.getAll();
		int initRow = 1;
		for (Producto p : productos) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(p.getIdProducto());
			row.createCell(1).setCellValue(p.getDescripcionCorta());
			row.createCell(2).setCellValue(p.getDescripcionLarga());
			row.createCell(3).setCellValue("in stock");
			row.createCell(4).setCellValue("new");
			row.createCell(5).setCellValue(p.getPrecio());
			row.createCell(6).setCellValue("/productos/"+p.getIdProducto());
			row.createCell(7).setCellValue(p.getImagen());
			row.createCell(8).setCellValue(p.getMarca().getNombre());

			initRow++;
		}

		workbook.write(stream);
		workbook.close();
		return new ByteArrayInputStream(stream.toByteArray());
	}
	
	
}//Fin class