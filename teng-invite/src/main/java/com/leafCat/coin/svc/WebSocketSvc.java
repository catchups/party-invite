package com.leafCat.coin.svc;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class WebSocketSvc {
	
	@Value("${upbit.ws.test}")
	String test;
	
    public void StartCon() throws InterruptedException, IOException {


    }

}
