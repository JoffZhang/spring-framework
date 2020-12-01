package com.joffzhang.annotation.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zy
 * @date 2020/12/1 15:39
 */
public class BaseTest {


	public void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}
}
