package com.spring.boot.security.springbootapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.security.springbootapp.entity.ItemEntity;

@Repository
public interface ItemRepositorySBA extends CrudRepository<ItemEntity, Long> {

	public List<ItemEntity> findByItemName(String itemName);
}
