package com.leafCat.coin.vo;

public class ExchangeVO {
	private int curId;
	private String curSymbol;
	private double exRate;
	
	public int getCurId() {
		return curId;
	}
	public void setCurId(int curId) {
		this.curId = curId;
	}
	public String getCurSymbol() {
		return curSymbol;
	}
	public void setCurSymbol(String curSymbol) {
		this.curSymbol = curSymbol;
	}
	public double getExRate() {
		return exRate;
	}
	public void setExRate(double exRate) {
		this.exRate = exRate;
	}
	
	@Override
	public String toString() {
		return "ExchangeVO [curId=" + curId + ", curSymbol=" + curSymbol + ", exRate=" + exRate + "]";
	}
	
	

}
