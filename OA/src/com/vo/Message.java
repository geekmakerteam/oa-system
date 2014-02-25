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
 * message 用于提示信息 
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
	 * 操作成功 
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 常规信息 
	 */
	public static final String INFO = "info";
	/** 
	 * 未登录时返回
	 */
	public static final String LOGIN = "login";
	
	/**
	 * 操作失败 
	 */
	public static final String ERROR = "error";
	
	@Autowired
	private MessageSource messageSource;
	
	private Locale locale;
	// 信息内容
	private String message;
	// 信息类型
	private String type;
	/**
	 * 默认构造参数
	 *  type 默认 SUCCESS
	 *  message 默认 SUCCESS
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
	 *  设置locale
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}

	@Override
	public String toString() {
		return JsonUtil.ObjectToString(this);
	}
}
