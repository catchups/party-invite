package com.leafCat.coin.jsonVO;

import com.google.gson.annotations.SerializedName;

public class BithumbDataVO {
	
	@SerializedName("type")
	String type;
	
	@SerializedName("price")
	double price;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
