package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.service.UserService;
import com.vo.Message;

@Controller
public class LoginController {
	
	public static final String ACCOUNT_USER_KEY = "account_user_key";
	@Autowired
	private UserService userService;
	@Autowired
	private Message message;
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody Message login(String  username,String password,HttpServletRequest req) {
		if(StringUtils.hasLength(username) && StringUtils.hasLength(password)){
			User user = userService.getByName(username);
			if(user == null || !user.getPassword().equals(password)){
				 this.message.setMessage("password.error",Message.ERROR);
			}else {
				req.getSession().setAttribute(ACCOUNT_USER_KEY, user);
				this.message.setMessage("success");
			}
		}else {
			 this.message.setMessage("input.empty",Message.ERROR);
		}
		 return this.message;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public Message getMessage() {
		return message;
	}


	public void setMessage(Message message) {
		this.message = message;
	}
	
}
