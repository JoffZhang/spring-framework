package com.joffzhang.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zy
 * @date 2020/12/10 15:55
 */
public class MyFirstInterceptor implements HandlerInterceptor {
	//在实际的处理程序运行之前
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("MyFirstInterceptor#preHandle");
		return true;
	}
	//处理程序运行后
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("MyFirstInterceptor#postHandle");

	}
	//完整请求完成后
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("MyFirstInterceptor#afterCompletion");

	}
}
