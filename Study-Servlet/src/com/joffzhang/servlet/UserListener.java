package com.joffzhang.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听项目的启动停止
 * @author zy
 * @date 2020/12/9 15:08
 */
public class UserListener implements ServletContextListener {

	//监听ServletContext启动初始化
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("UserListener...contextInitialized...");
	}
	//监听ServletContext销毁
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		System.out.println("UserListener...contextDestroyed...");
	}
}
