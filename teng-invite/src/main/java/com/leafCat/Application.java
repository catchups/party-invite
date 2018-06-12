package com.leafCat;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableScheduling
@Controller
public class Application extends SpringBootServletInitializer{
	
	@Value("${upbit.ws.test}")
	static String test;
	
	private static Logger logger = Logger.getLogger(Application.class);
	
	public static void main(String[] args) throws InterruptedException, IOException {
		SpringApplication.run(Application.class, args);
		logger.info("========================== Server Sterted ================================");
	}
	
}
