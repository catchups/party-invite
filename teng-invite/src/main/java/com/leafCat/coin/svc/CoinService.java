package com.leafCat.coin.svc;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.leafCat.coin.jsonVO.BithumbVO;
import com.leafCat.coin.jsonVO.UpbitVO;
import com.leafCat.coin.mapper.CoinGapMapper;
import com.leafCat.coin.mapper.CoinMapper;
import com.leafCat.coin.vo.CoinGapVO;
import com.leafCat.coin.vo.CoinVO;
import com.leafCat.coin.vo.ExchangeVO;

@Service
public class CoinService {
	
	@Autowired
	CoinMapper coinMapper;
	
	@Autowired
	CoinGapMapper coinGapMapper;
	
	public double getCoinInfo(int marketId, String coinSymbol){
		double rtnVal = -1 ;
		// RestTemplate 에 MessageConverter 세팅
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		//bitfinex
		if(marketId == 1){
			// REST API 호출
			String result = restTemplate.getForObject("https://api.bitfinex.com/v2/trades/t"+coinSymbol+"USD/hist", String.class);
			JSONArray data = new JSONArray(result).getJSONArray(0);
			rtnVal = Double.parseDouble(data.get(3).toString());
		}
		//Bithumb
		else if(marketId == 2){
			// REST API 호출
			String result = restTemplate.getForObject("https://api.bithumb.com/public/recent_transactions/" + coinSymbol, String.class);
			Gson gson = new Gson();
			BithumbVO vo = gson.fromJson(result, BithumbVO.class);
			if(vo != null && vo.getData() != null && vo.getData().get(0) != null){
				rtnVal = vo.getData().get(0).getPrice();
			}
		}
		//upbit
		else if (marketId == 3) {
		//	String result = restTemplate.getForObject("https://crix-api-endpoint.upbit.com/v1/crix/candles/minutes/30?code=CRIX.UPBIT.KRW-"+coinSymbol+"&count=1", String.class);
			String result = restTemplate.getForObject("https://api-beta.upbit.com/v1/ticker?markets=KRW-"+coinSymbol, String.class);
			result = result.replace("[", "");
			result = result.replace("]", "");
			
			Gson gson = new Gson();
			UpbitVO vo = gson.fromJson(result, UpbitVO.class);
			if(vo != null){
				rtnVal = vo.getPrice();
			}
		}
		
		return rtnVal;
	}
	
	public ExchangeVO getExchangeRate(ExchangeVO exchangeVO){
		return coinMapper.selectOneExchangeRate(exchangeVO);
	}
	
	public void updateCoinPrice(CoinVO coinVO){
		coinMapper.updateCoinPrice(coinVO);
	}
	
	public void updateExchangeRate(ExchangeVO exchangeVO){
		coinMapper.updateExchangeRate(exchangeVO);
	}

	public List<CoinVO> selectCoinPriceList(CoinVO coinVO) {
		return coinMapper.selectCoinPriceList(coinVO);
	}
	
	public void insertGapData(CoinGapVO gapVO){
		coinGapMapper.insertCoinGapData(gapVO);
	}

}
