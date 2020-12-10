package com.joffzhang;

import com.joffzhang.config.AppConfig;
import com.joffzhang.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web容器启动的时候创建对象；调用方法来初始化容器以及前端控制器
 * @author zy
 * @date 2020/12/9 17:32
 */

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	//获取跟容器的配置类	(Spring的配置文件)	父容器
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}
	//获取web容器的配置类	(SpringMvc配置文件)	子容器
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{AppConfig.class};
	}
	//获取DispatcherServlet的映射信息
	// /:拦截所有请求（包括静态资源（xxx.js,xxx.png）），但是不包括*.jsp
	// /*:拦截所有请求	包括*.jsp;	jsp页面是tomcat的jsp引擎解析的；
	@Override
	protected String[] getServletMappings() {
		//return new String[0];
		return new String[]{"/"};
	}
}
