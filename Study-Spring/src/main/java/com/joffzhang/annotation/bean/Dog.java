package com.joffzhang.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zy
 * @date 2020/12/1 9:56
 */
@Component
public class Dog {


	public Dog(){
		System.out.println("  dog constructor");
	}
	//对象创建并赋值之后调用
	@PostConstruct
	public void postConstruct(){
		System.out.println("  dog @PostConstruct");
	}
	//容器移除对象之前
	@PreDestroy
	public void preDestroy(){
		System.out.println("  dog @PreDestroy");
	}


}
