package com.controller;
    
  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
@RequestMapping("index")
@Controller
public class IndexController{  
	
    @RequestMapping(value="success",method=RequestMethod.GET)  
    @ResponseBody 
    public String success() {  
    	return "get";
    }  

    @RequestMapping(value="success",method=RequestMethod.POST)  
    @ResponseBody
    public String success2() {  
    	return "post";
    }

    @RequestMapping(value="success",method=RequestMethod.PUT)  
    @ResponseBody
    public String success3() {  
    	return "put";
    }

    @RequestMapping(value="success",method=RequestMethod.DELETE)  
    @ResponseBody
    public String success4() {  
    	return  "delete";
    }
    
}  