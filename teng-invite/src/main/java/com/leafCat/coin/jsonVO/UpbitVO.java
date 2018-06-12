package com.leafCat.coin.jsonVO;

import com.google.gson.annotations.SerializedName;

public class UpbitVO {
	@SerializedName("trade_price")
	double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
