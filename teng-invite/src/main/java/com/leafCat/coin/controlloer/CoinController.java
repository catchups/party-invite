package com.leafCat.coin.controlloer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.leafCat.coin.svc.CoinService;
import com.leafCat.coin.svc.TelegramApiService;
import com.leafCat.coin.svc.WebSocketSvc;
import com.leafCat.coin.vo.CoinVO;
import com.leafCat.coin.vo.ExchangeVO;


@RestController
public class CoinController {
	
	private static Logger logger = Logger.getLogger(CoinController.class);

	
	String[] testSymbol = {"XVG","EOSDAC","WAX","LOOM","ADT","VIB","CND","DMT","BLOCK","GNO","LRC","NMR"};
	
	List<Symbol> symbolList = new ArrayList<Symbol>();
	
	
	public CoinController() {
		for (int i = 0; i < testSymbol.length; i++) {
			symbolList.add(new Symbol(testSymbol[i], 0));
		}
	}
	
	class Symbol{
		String name;
		int status;
		
		public Symbol(String name, int status) {
			this.name = name;
			this.status = status;
		}
	}
	
	@Autowired
	TelegramApiService telegramApiService;
	
	@Autowired
	CoinService coinService;

	@Autowired
	WebSocketSvc webSocketSvc;
	
//	@Scheduled(cron="0/10 * * * * ?")
	public void listingBot2() throws InterruptedException, IOException{
		webSocketSvc.StartCon();
	}
	
	
//	@Scheduled(cron="0/10 * * * * ?")
	public void listingBot(){
		for (int i = 0; i < symbolList.size(); i++) {
			
			String thisSymbol = symbolList.get(i).name;
			
			if (symbolList.get(i).status == 0 ) {
				try {
					coinService.getCoinInfo(3, thisSymbol);
					logger.info(symbolList.get(i).name + " API 감지");
					telegramApiService.sendMSG("577023742:AAEH0SgepYl3XxRvNudGvlEVoFPzMWAXofg","upbit " + thisSymbol  + " 원화 API 감지", "-1001317239552");
					
					symbolList.get(i).status = 1;
				} catch (HttpClientErrorException e) {
					continue;
				}
			}
			
			if(symbolList.get(i).status == 1) {
				try {
					coinService.getCoinInfo(3, thisSymbol);
				} catch (HttpClientErrorException e) {
					logger.info(symbolList.get(i).name + " API 삭제됨");
					telegramApiService.sendMSG("577023742:AAEH0SgepYl3XxRvNudGvlEVoFPzMWAXofg","upbit " + thisSymbol  + " 원화 API 감지", "-1001317239552");
					symbolList.get(i).status = 0;
					continue;
				}
			}
		}
	};

	
//	@Scheduled(cron="0/3 * * * * ?")
	public void priceBot(){
		
		double price_bithumb = coinService.getCoinInfo(2, "EOS");
		double price_upbit = coinService.getCoinInfo(3, "EOS");
		
		double dif = price_bithumb - price_upbit; 
		
		
		System.out.println("빗썸 : " + price_bithumb + " / 업빗 : " + price_upbit );
		System.out.println("차이 : " + dif );
		
	};
	
	
	
//	@Scheduled(cron="0/6 * * * * ?")
	public void BithumbAPICall(){
//		DecimalFormat df = new DecimalFormat("#.#");
		//System.out.println(df.format(coinService.getCoinInfo(1, "BTC")));
		CoinVO coinVO = new CoinVO();
		coinVO.setMarketId(2);
		coinVO.setCurrency("KRW");
		
		callUpdateService(coinVO, "BTC"); //BTC
		callUpdateService(coinVO, "BTG"); //BTG
		callUpdateService(coinVO, "DASH"); //DASH
		callUpdateService(coinVO, "EOS"); //EOS
		callUpdateService(coinVO, "ETC"); //ETC
		callUpdateService(coinVO, "ETH"); //ETH
		callUpdateService(coinVO, "LTC"); //LTC
		callUpdateService(coinVO, "QTUM"); //QTUM
		callUpdateService(coinVO, "XMR"); //XMR
		callUpdateService(coinVO, "XRP"); //XRP
		callUpdateService(coinVO, "ZEC"); //ZEC
		callUpdateService(coinVO, "BCH"); //BCH
	}
	
//	@Scheduled(cron="0/6 * * * * ?")
	public void upbitAPICall(){
		CoinVO coinVO = new CoinVO();
		coinVO.setMarketId(3);
		coinVO.setCurrency("KRW");
		
		callUpdateService(coinVO, "BTC"); //BTC
		callUpdateService(coinVO, "BTG"); //BTG
		callUpdateService(coinVO, "DASH"); //DASH
		callUpdateService(coinVO, "EOS"); //EOS
		callUpdateService(coinVO, "ETC"); //ETC
		callUpdateService(coinVO, "ETH"); //ETH
		callUpdateService(coinVO, "LTC"); //LTC
		callUpdateService(coinVO, "QTUM"); //QTUM
		callUpdateService(coinVO, "XMR"); //XMR
		callUpdateService(coinVO, "XRP"); //XRP
		callUpdateService(coinVO, "ZEC"); //ZEC
		callUpdateService(coinVO, "BCH", "BCC"); //BCH -> upbit만 BCC
	}
	
//	@Scheduled(cron="0 0/1 * 1/1 * ?")
	public void bitfinexAPICall(){
		CoinVO coinVO = new CoinVO();
		coinVO.setMarketId(1);
		coinVO.setCurrency("USD");

		try {
			callUpdateService(coinVO, "BTC"); //BTC
			Thread.sleep(2000);
			callUpdateService(coinVO, "BTG"); //BTG
			Thread.sleep(2000);
			callUpdateService(coinVO, "DASH", "DSH"); //DASH
			Thread.sleep(2000);
			callUpdateService(coinVO, "EOS"); //EOS
			Thread.sleep(2000);
			callUpdateService(coinVO, "ETC"); //ETC
			Thread.sleep(2000);
			callUpdateService(coinVO, "ETH"); //ETH
			Thread.sleep(2000);
			callUpdateService(coinVO, "LTC"); //LTC
			Thread.sleep(2000);
			callUpdateService(coinVO, "QTUM", "QTM"); //QTUM
			Thread.sleep(2000);
			callUpdateService(coinVO, "XMR"); //XMR
			Thread.sleep(2000);
			callUpdateService(coinVO, "XRP"); //XRP
			Thread.sleep(2000);
			callUpdateService(coinVO, "ZEC"); //ZEC
			Thread.sleep(2000);
			callUpdateService(coinVO, "BCH"); //BCH
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @RequestMapping(value = "/getCoinPrice", method=RequestMethod.GET)
    private ModelAndView getCoinPrice(HttpServletRequest request) throws Exception {

    	ModelAndView mv = new ModelAndView("jsonView");
		
		List<CoinVO> coinPriceList = coinService.selectCoinPriceList(new CoinVO());
		
    	ExchangeVO exchangeVO = new ExchangeVO();
    	exchangeVO.setCurSymbol("USD");
    	exchangeVO = coinService.getExchangeRate(exchangeVO);
		
		mv.addObject("data", coinPriceList);
		mv.addObject("exchange",exchangeVO);
		
		return mv;
    }
    
    @RequestMapping(value = "/updateExchangeRate", method=RequestMethod.GET)
    private ModelAndView updateExchangeRate(@ModelAttribute ExchangeVO exchangeVO, HttpServletRequest request) throws Exception {
    	
    	//임시- 비밀번호 일치시에만 환율 업데이트
    	if("roqkf12!".equals(request.getParameter("pwd"))){
    		coinService.updateExchangeRate(exchangeVO);
    	}
    	
    	return new ModelAndView("main");
    }

	
	private void callUpdateService(CoinVO coinVO, String symbol){
		coinVO.setCoinSymbol(symbol);
		coinVO.setPrice(coinService.getCoinInfo(coinVO.getMarketId(), symbol));
		coinService.updateCoinPrice(coinVO);
	}
	
	private void callUpdateService(CoinVO coinVO, String symbol, String symbol2) {
		coinVO.setCoinSymbol(symbol);
		coinVO.setPrice(coinService.getCoinInfo(coinVO.getMarketId(), symbol2));
		coinService.updateCoinPrice(coinVO);
	}
	

}
