package com.joffzhang.config;

import com.joffzhang.interceptor.MyFirstInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * MVC定制配置
 * 	可实现 {@link WebMvcConfigurer}	子类仅覆盖其感兴趣的方法
 *  由于WebMvcConfigurer顶级接口需要实现方法较多
 * 	可继承适配类{@link WebMvcConfigurerAdapter}	该类实现默认方法都为空
 *
 * 	由于1.8 接口方法default默认方法功能，所以WebMvcConfigurerAdapter将在不久删除
 * @author zy
 * @date 2020/12/10 10:34
 *
 *
 * 需要此配置生效，需修改AppConfig、RootConfig加载扫描配置
 * AppConfig : web相关
 * RootConfig : bean相关
 */

//启用MVC配置
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	//定制

	//	视图解析器
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		//默认所有页面jsp("/WEB-INF/", ".jsp")
		registry.jsp("/WEB-INF/view-jsp/",".jsp");
	}

	//	静态资源访问

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	//注册拦截器以应用于传入的请求
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyFirstInterceptor()).addPathPatterns("/**");
	}
}
