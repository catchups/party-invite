package com.leafCat.coin.mapper;

import java.util.List;

import com.leafCat.coin.vo.CoinVO;
import com.leafCat.coin.vo.ExchangeVO;

public interface CoinMapper {
	
	public void updateCoinPrice(CoinVO vo);

	public List<CoinVO> selectCoinPriceList(CoinVO coinVO);
	
	public void updateExchangeRate(ExchangeVO exchangeVO);
	
	public ExchangeVO selectOneExchangeRate(ExchangeVO exchangeVO);

}
