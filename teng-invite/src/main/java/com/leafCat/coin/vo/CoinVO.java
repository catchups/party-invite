package com.leafCat.coin.vo;

public class CoinVO {
	
	private String coinSymbol;
	private int marketId;
	private double price;
	private String coinNameKr;
	private String coinNameUs;
	private String currency;
	public String getCoinSymbol() {
		return coinSymbol;
	}
	public void setCoinSymbol(String coinSymbol) {
		this.coinSymbol = coinSymbol;
	}
	public int getMarketId() {
		return marketId;
	}
	public void setMarketId(int marketId) {
		this.marketId = marketId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCoinNameKr() {
		return coinNameKr;
	}
	public void setCoinNameKr(String coinNameKr) {
		this.coinNameKr = coinNameKr;
	}
	public String getCoinNameUs() {
		return coinNameUs;
	}
	public void setCoinNameUs(String coinNameUs) {
		this.coinNameUs = coinNameUs;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
