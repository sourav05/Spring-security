package com.spring.boot.security.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spring.boot.security.springbootapp.util.Constants.ITEM_CATEGORY;

@Entity
@Table(name="TBL_ITEM")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="ITEM_NAME", nullable=false)
	private String itemName;
	
	@Column(name="ITEM_CATEGORY", nullable=false)
	private ITEM_CATEGORY itemCategory;
	
	@Column(name="ITEM_PRICE", nullable=false)
	private double itemPrice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ITEM_CATEGORY getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ITEM_CATEGORY itemCategory) {
		this.itemCategory = itemCategory;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
}
