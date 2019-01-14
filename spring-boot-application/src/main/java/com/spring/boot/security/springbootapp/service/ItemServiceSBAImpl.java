package com.spring.boot.security.springbootapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.springbootapp.dao.ItemRepositorySBA;
import com.spring.boot.security.springbootapp.entity.ItemEntity;
import com.spring.boot.security.springbootapp.util.AppUtil;

@Service
public class ItemServiceSBAImpl implements ItemServiceSBA {

	@Autowired
	private ItemRepositorySBA itemRepository;
	
	@Override
	public ItemEntity findById(long id) {
		return itemRepository.findById(id).orElse(new ItemEntity());
	}

	@Override
	public List<ItemEntity> getAllItem() {
		return AppUtil.convertIterableToList(itemRepository.findAll());
	}

	@Override
	public List<ItemEntity> findByItemName(String itemName) {
		return itemRepository.findByItemName(itemName);
	}

	@Override
	public ItemEntity saveItem(ItemEntity item) {
		itemRepository.save(item);
		return item;
	}
}
