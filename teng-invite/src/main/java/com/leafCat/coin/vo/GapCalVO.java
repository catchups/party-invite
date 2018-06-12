package com.leafCat.coin.vo;

public class GapCalVO {

	public String symbol;
	public int flag = 0;
	public double gap = 0;
	public int cnt = 0;
	public long time;
	
	public double upbicPrice = 0;
	public double bithumbPrice = 0;
	
	public GapCalVO(String symbol) {
		this.symbol = symbol;
	}
}
