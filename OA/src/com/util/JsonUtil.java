package com.util;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	public static String ObjectToString(Object object) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return	objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			
		}
		return "type:error";
	}
	
}
