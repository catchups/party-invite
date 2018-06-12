package com.leafCat.common.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static Logger logger = Logger.getLogger(JsonUtil.class.getClass());
	
	public static String objectToJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	public static Object jsonStringToObject(String jsonInString, Class<?> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(jsonInString, clazz);
		} catch (JsonParseException e) {
			logger.error("err", e);
			return null;
		} catch (JsonMappingException e) {
			logger.error("err", e);
			return null;
		} catch (IOException e) {
			logger.error("err", e);
			return null;
		}
	}
}
