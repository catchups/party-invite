package com.leafCat.coin.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramApiService {

	public String sendMSG(String botId, String msg, String chatId) {
		
		String result="";
		String url = "https://api.telegram.org/bot"+botId+"/sendMessage?chat_id="+ chatId + "&text=" + msg ;
		
		// RestTemplate 에 MessageConverter 세팅
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(converters);
		
		//System.out.println(url);
		
		result =  restTemplate.getForObject(url, String.class);
		
		return result;
		
		
	}
}
