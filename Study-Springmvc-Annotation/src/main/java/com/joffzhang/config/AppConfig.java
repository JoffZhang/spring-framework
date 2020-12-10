package com.joffzhang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zy
 * @date 2020/12/9 17:40
 */
//springMVC的容器只扫描controller;子容器
//useDefaultFilters=false 禁用默认过滤规则；
@ComponentScan(value = "com.joffzhang",includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
		//为使定制配置 WebConfig 生效 ,需在RootConfig中排除扫描,	在AppConfig中添加扫描,	目的：将WebConfig扫描到 Servlet WebApplicationContext中
		,@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {WebConfig.class})
},useDefaultFilters = false)
//@EnableWebMvc
public class AppConfig {
//		implements WebMvcConfigurer {
//
//	//定制
//
//	//	视图解析器
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		//默认所有页面jsp("/WEB-INF/", ".jsp")
//		registry.jsp("/WEB-INF/view-jsp/",".jsp");
//
//	}
}