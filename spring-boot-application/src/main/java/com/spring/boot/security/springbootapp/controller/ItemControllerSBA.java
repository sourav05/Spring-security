package com.spring.boot.security.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.security.springbootapp.entity.ItemEntity;
import com.spring.boot.security.springbootapp.service.ItemServiceSBA;

@RestController
@RequestMapping(value="/item")
public class ItemControllerSBA {
	
	@Autowired
	private ItemServiceSBA itemService;
	
	@GetMapping(value="/default")
	public ItemEntity getItemDetails(){
		return new ItemEntity();
	}
	
	@GetMapping(value="/all")
	public List<ItemEntity> getAllItem(){
		return itemService.getAllItem();
	}
	
	@GetMapping(value="/id/{id}")
	public ItemEntity getItem(@PathVariable(name="id") long id){
		return itemService.findById(id);
	}
	
	@GetMapping(value="/name/{itemName}")
	public List<ItemEntity> getItem(@PathVariable(name="itemName") String itemName){
		return itemService.findByItemName(itemName);
	}
	
	@PostMapping(value="/save")
	public ItemEntity saveItem(@RequestBody ItemEntity entity){
		return itemService.saveItem(entity);
	}
	
//	@ExceptionHandler(UsernameNotFoundException.class)
//	public ResponseEntity<String> handleException(UsernameNotFoundException e){
//		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
//	}
}
