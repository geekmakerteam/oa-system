package com.vo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.util.JsonUtil;

/**
 * message ������ʾ��Ϣ 
 * @author xue__lang
 */
/**
 * @author xue__lang
 *
 */
@Component
@Scope(value="prototype")
public class Message{
	
	/**
	 * �����ɹ� 
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * ������Ϣ 
	 */
	public static final String INFO = "info";
	/** 
	 * δ��¼ʱ����
	 */
	public static final String LOGIN = "login";
	
	/**
	 * ����ʧ�� 
	 */
	public static final String ERROR = "error";
	
	@Autowired
	private MessageSource messageSource;
	
	private Locale locale;
	// ��Ϣ����
	private String message;
	// ��Ϣ����
	private String type;
	/**
	 * Ĭ�Ϲ������
	 *  type Ĭ�� SUCCESS
	 *  message Ĭ�� SUCCESS
	 *  locale Locale.CHINA
	 */
	public Message() {
		this.type = SUCCESS;
		this.message = SUCCESS;
		this.locale = Locale.CHINA;
	}
	
	public void setMessage(String message) {
		this.message =  messageSource.getMessage(message, null, message, locale);
	}
	
	
	public void setMessage(String message,String type) {
		this.setMessage(message);
		this.type = type;
	}
	
	public void setMessage(String message,Object[] objects) {
		this.messageSource.getMessage(message,objects,message,locale);
	}
	
	public void setMessage(String message,String type,Object[] objects) {
		this.setMessage(message,objects);
		this.type = type;
	}
	
	public void setMessage(String message,String type,Object[] objects,Locale locale) {
		this.messageSource.getMessage(message,objects,message,locale);
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 *  ����locale
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}

	@Override
	public String toString() {
		return JsonUtil.ObjectToString(this);
	}
}
