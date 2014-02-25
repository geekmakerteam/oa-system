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

/*	�÷�������Controller�ķ���ִ��ǰ�ᱻ���ã�����ʹ������������жϻ��߼���ִ�����Ĵ���
 * ������trueʱ������ִ�����������������falseʱ���򲻻�ȥִ��Controller�ķ�����
 * ����֤�û��Ƿ��½����ʹ��preHandleAction������������ӣ�*/
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
