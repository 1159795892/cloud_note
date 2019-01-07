package com.pj.cloud_note.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SomeInterceptor implements HandlerInterceptor{

	/**
	 * DispatcherServlet收到请求之后,会先调用preHandle方法.
	 * 如果该方法的返回值为true,则继续向后调用;如果该方法的返回值
	 * 为false,则不再调用;arg:描述处理器方法的一个对象
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle()");
		HttpSession session=request.getSession();
		Object obj=session.getAttribute("name");
		if (obj==null) {
			//没有登录,重定向到登录页面
			response.sendRedirect("/user/toLogin.do");
			return false;
		}
		//已经登陆过,允许访问
		return true;
	}
	/**
	 * 处理器(Controller)的方法已经执行完毕,正传被处理结果(ModelAndView对象)返回
	 * DispatcherServlet之前执行postHandle方法.可以再改方法里面,修改处理结果.
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}
