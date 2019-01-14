package com.spring.boot.security.springbootapp.service;

import java.util.List;

import com.spring.boot.security.springbootapp.entity.ItemEntity;

public interface ItemServiceSBA {

	public ItemEntity findById(long id);
	
	public List<ItemEntity> getAllItem();
	
	public List<ItemEntity> findByItemName(String itemName);
	
	public ItemEntity saveItem(ItemEntity item);
}
