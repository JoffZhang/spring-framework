package com.joffzhang.annotation.config;

import com.joffzhang.annotation.bean.Person;
import com.joffzhang.annotation.service.TestService;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

/**
 * @Configuration
 * @Bean
 * @ComponentScan
 * 	@Filter
 * @ComponentScans
 */
//配置类==配置文件
@Configuration	//告诉spring这是一个配置类
@ComponentScans(value = {
//		@ComponentScan(value = "com.joffzhang.annotation",excludeFilters = {
//				@Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
//		})
 		@ComponentScan(value = "com.joffzhang.annotation", includeFilters = {
				@Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
				@Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {TestService.class}),
				@Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
		},useDefaultFilters = false)
})
//@ComponentScan value：指定扫描的包
//excludeFilters = Filter[]	指定排除规则
//includeFilters = Filter[] 指定包含规则  默认规则是扫描全部,useDefaultFilter=false
// FilterType.ANNOTATION 	按注解
// FilterType.ASSIGNABLE_TYPE 按类型
// FilterType.ASPECTJ	按ASPECTJ表达式
// FilterType.REGEX 	按正则
// FilterType.CUSTOM	自定义规则
public class MainConfig {

	//给容器中注册一个Bean，类型为返回值的类型，id默认是方法名
	@Bean
	public Person person(){
		return new Person("meinv",12);
	}

}
