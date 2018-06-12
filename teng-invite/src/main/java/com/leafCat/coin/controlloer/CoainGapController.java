package com.leafCat.coin.controlloer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.leafCat.coin.svc.CoinService;
import com.leafCat.coin.svc.TelegramApiService;
import com.leafCat.coin.vo.GapCalVO;
import com.leafCat.coin.vo.CoinGapVO;

@RestController
public class CoainGapController {
	
	private static Logger logger = Logger.getLogger(CoainGapController.class);
	
	@Autowired
	TelegramApiService telegramApiService;
	
	@Autowired
	CoinService coinService;
	
	static double GAP_PERCENT = 0.6;
	
	static GapCalVO eosGap = new GapCalVO("EOS"); 
	static GapCalVO icxGap = new GapCalVO("ICX"); 
	static GapCalVO xrpGap = new GapCalVO("XRP"); 
	static GapCalVO etcGap = new GapCalVO("ETC"); 
	static GapCalVO qtumGap = new GapCalVO("QTUM"); 
	static GapCalVO trxGap = new GapCalVO("TRX"); 
	
	
//	@Scheduled(cron="0/2 * * * * ?")
	public void eosCoinGap(){
		calGap("EOS", eosGap, GAP_PERCENT);
	}
	
//	@Scheduled(cron="0/2 * * * * ?")
	public void icxCoinGap(){
		calGap("ICX", icxGap, GAP_PERCENT);
	}

//	@Scheduled(cron="0/2 * * * * ?")
	public void xrpCoinGap(){
		calGap("XRP", xrpGap, GAP_PERCENT);
	}
	
//	@Scheduled(cron="0/2 * * * * ?")
	public void etcCoinGap(){
		calGap("ETC", etcGap, GAP_PERCENT);
	}
	
//	@Scheduled(cron="0/2 * * * * ?")
	public void qtumCoinGap(){
		calGap("QTUM", qtumGap, GAP_PERCENT);
	}
	
//	@Scheduled(cron="0/2 * * * * ?")
	public void trxCoinGap(){
		calGap("TRX", trxGap, GAP_PERCENT);
	}
	
	public void calGap(String symbol, GapCalVO calVO, double percent){
		double bithumbPrice = coinService.getCoinInfo(2, symbol);
		double upbitPrice = coinService.getCoinInfo(3, symbol);
		double dif = bithumbPrice - upbitPrice; 
		double calDif = Double.min(bithumbPrice, upbitPrice) * percent * 0.01;
		
//		System.out.println("빗썸 : " + bithumbPrice + " / 업빗 : " + upbitPrice );
//		System.out.println("차이 : " + dif );
//		System.out.println(calDif);
		
		
		if(calVO.flag == 0){
			if(Math.abs(dif) >= calDif){
				logger.info("---------------- " + symbol + " GAP 발생--------------------");
				calVO.flag = 1;
				calVO.bithumbPrice += bithumbPrice;
				calVO.upbicPrice += upbitPrice;
				calVO.cnt ++;
				calVO.gap += dif;
				calVO.time = System.currentTimeMillis();
				telegramApiService.sendMSG("614585239:AAHCLwPME6BuCRSu6ZdVFiAGWF9r3Ds3IeQ", symbol +" GAP 발생 : upbit " + upbitPrice  + ", 빗썸 : "+ bithumbPrice + " ,GAP : "+ String.format("%.2f", dif) ,"502464124" );
			}
		}else if(calVO.flag == 1){
			if(Math.abs(dif) < calDif){
				CoinGapVO gapVO = new CoinGapVO();
				gapVO.setCoinSymbol(symbol);
				gapVO.setUpbitPrice(decimalFix(calVO.upbicPrice / calVO.cnt, 2));
				gapVO.setBithumbPrice(decimalFix(calVO.bithumbPrice / calVO.cnt, 2) );
				gapVO.setDuration((int)(System.currentTimeMillis() - calVO.time)/1000);
				gapVO.setGap(decimalFix( Math.abs(calVO.gap / calVO.cnt), 2));
				gapVO.setGapPercent(decimalFix(Math.abs(gapVO.getGap() / gapVO.getUpbitPrice() * (double)100), 2));
				
				if(calVO.bithumbPrice > calVO.upbicPrice){
					gapVO.setGapType(1);
				}else{
					gapVO.setGapType(2);
				}
				
				calVO.flag = 0;
				calVO.bithumbPrice = 0;
				calVO.upbicPrice = 0;
				calVO.cnt = 0;
				calVO.gap = 0;
				
				logger.info("---------------- " + symbol + " GAP 종료-------------------- " + gapVO.toString());
				coinService.insertGapData(gapVO);
			}else{
				calVO.bithumbPrice += bithumbPrice;
				calVO.upbicPrice += upbitPrice;
				calVO.cnt ++;
				calVO.gap += dif;
			}
			
		}
	}
	
	public double decimalFix(double data, int point){
		return Double.parseDouble(String.format("%."+point+"f", data));
	}
	

}
