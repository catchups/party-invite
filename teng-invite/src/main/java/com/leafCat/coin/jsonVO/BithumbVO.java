package com.leafCat.coin.jsonVO;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BithumbVO {
	
	@SerializedName("status")
	private int status;
	
	@SerializedName("data")
	private List<BithumbDataVO> data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<BithumbDataVO> getData() {
		return data;
	}

	public void setData(List<BithumbDataVO> data) {
		this.data = data;
	}
	
	

}
