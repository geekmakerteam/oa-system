package com.controller;
    
  
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.form.PageForm;
import com.service.UserService;
import com.vo.Page;
 
@RequestMapping("/user/*")
@Controller
public class UserController{  
	
	@Autowired
	private UserService userService;
	
    @RequestMapping(value="list",method=RequestMethod.GET)  
    @ResponseBody 
    public Page<User> list(@Valid PageForm form) {  
    	
    	return userService.findBypage(form);
    }  

}  