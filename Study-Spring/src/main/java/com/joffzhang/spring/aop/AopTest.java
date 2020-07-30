package com.joffzhang.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zy
 * @date 2020/7/28 11:27
 */
public class AopTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/joffzhang/spring/aop/aop.xml");
		TestBean testBean = (TestBean) applicationContext.getBean("test");
		testBean.test();
	}
}
