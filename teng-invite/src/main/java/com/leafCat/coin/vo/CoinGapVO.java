package com.leafCat.coin.vo;

/**
 * @author 박명순
 *
 */
public class CoinGapVO {
	
	private String coinSymbol;
	private double upbitPrice;
	private double bithumbPrice;
	private double gap;
	private double gapPercent;
	private int duration;
	private int gapType;
	
	private String firstCreateDatetime;

	public String getCoinSymbol() {
		return coinSymbol;
	}

	public void setCoinSymbol(String coinSymbol) {
		this.coinSymbol = coinSymbol;
	}

	public double getUpbitPrice() {
		return upbitPrice;
	}

	public void setUpbitPrice(double upbitPrice) {
		this.upbitPrice = upbitPrice;
	}

	public double getBithumbPrice() {
		return bithumbPrice;
	}

	public void setBithumbPrice(double bithumbPrice) {
		this.bithumbPrice = bithumbPrice;
	}

	public double getGap() {
		return gap;
	}

	public void setGap(double gap) {
		this.gap = gap;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getFirstCreateDatetime() {
		return firstCreateDatetime;
	}

	public void setFirstCreateDatetime(String firstCreateDatetime) {
		this.firstCreateDatetime = firstCreateDatetime;
	}
	
	public double getGapPercent() {
		return gapPercent;
	}

	public void setGapPercent(double gapPercent) {
		this.gapPercent = gapPercent;
	}
	public int getGapType() {
		return gapType;
	}

	public void setGapType(int gapType) {
		this.gapType = gapType;
	}

	@Override
	public String toString() {
		return "CoinGapVO [coinSymbol=" + coinSymbol + ", upbitPrice=" + upbitPrice + ", bithumbPrice=" + bithumbPrice
				+ ", gap=" + gap + ", gapPercent=" + gapPercent + ", duration=" + duration + ", gapType=" + gapType
				+ ", firstCreateDatetime=" + firstCreateDatetime + "]";
	}

	
}
