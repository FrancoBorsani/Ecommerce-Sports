package com.ecommercesports.ecommercesports.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecommercesports.ecommercesports.converters.ItemConverter;
import com.ecommercesports.ecommercesports.entities.Carrito;
import com.ecommercesports.ecommercesports.entities.Item;
import com.ecommercesports.ecommercesports.models.CarritoModel;
import com.ecommercesports.ecommercesports.models.ItemModel;
import com.ecommercesports.ecommercesports.repositories.IItemRepository;
import com.ecommercesports.ecommercesports.services.IItemService;

@Service("itemService")
public class ItemService implements IItemService{
	@Autowired
	@Qualifier("itemRepository")
	private IItemRepository itemRepository;
	
	@Autowired
	@Qualifier("itemConverter")
	private ItemConverter itemConverter;
	
	@Override
	public List<Item> getAll(){
		
		return itemRepository.findAll();
		
	}
	@Override
	public ItemModel findByIdItem(long idItem) {	
		return itemConverter.entityToModel(itemRepository.findByIdItem(idItem));
		
	}
	
	
	
	
	
	@Override
	public ItemModel insertOrUpdate(ItemModel itemModel) {
		Item item = itemRepository.save(itemConverter.modelToEntity(itemModel));
		return itemConverter.entityToModel(item);
	}
	@Override
	public boolean remove(long idItem) {
		
		try {
			
			itemRepository.deleteById(idItem);;
			return true;
		}catch(Exception e) {
			
			return false;
			
		}
		
	}
	
}//Fin class
