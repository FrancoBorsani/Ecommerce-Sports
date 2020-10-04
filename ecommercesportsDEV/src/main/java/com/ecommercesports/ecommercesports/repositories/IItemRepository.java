package com.ecommercesports.ecommercesports.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercesports.ecommercesports.entities.Item;

@Repository("itemRepository")
public interface IItemRepository extends JpaRepository<Item, Serializable> {

	public abstract Item findByIdItem(long idItem);

}//Fin interface 
