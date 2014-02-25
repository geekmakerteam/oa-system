package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.util.JsonUtil;
import com.util.MessageUtil;
import com.vo.Message;
@Component
public class MyInterceptor implements HandlerInterceptor{
	
	@Autowired
	private Message message;
	
	public MyInterceptor(){
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("postHandle");
	}

/*	该方法会在Controller的方法执行前会被调用，可以使用这个方法来中断或者继续执行链的处理，
 * 当返回true时，处理执行链会继续，当返回false时，则不会去执行Controller的方法。
 * （验证用户是否登陆就是使用preHandleAction方法的最好例子）*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		System.out.println("preHandle");
		 User  user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			message.setMessage("login.error",Message.LOGIN);
			String str = JsonUtil.ObjectToString(message);
			System.out.println(str);
			System.out.println("-----------------------------------------------------");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(str);
			return false;
		}
		 return true;
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}

}
