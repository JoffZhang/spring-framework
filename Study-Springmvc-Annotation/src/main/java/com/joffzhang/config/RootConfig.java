package com.joffzhang.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author zy
 * @date 2020/12/9 17:40
 */
//spring的容器不扫描controller,父容器
@ComponentScan(value = "com.joffzhang",excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
		//为使定制配置 WebConfig 生效 ,需在RootConfig中排除扫描,	在AppConfig中添加扫描,	目的：将WebConfig扫描到 Servlet WebApplicationContext中
		,@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {WebConfig.class})
})
public class RootConfig {
}
