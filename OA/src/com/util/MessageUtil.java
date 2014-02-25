package com.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vo.Message;
@Component
public class MessageUtil {
	
	@Autowired
	private static Message message;
	
	public static Message getMessage() {
		return message;
		
	}

	public static void  setMessage(Message message) {
		MessageUtil.message = message;
	}
	
}
