package com.joffzhang.spring.customtag;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zy
 * @date 2020/7/23 13:54
 */
public class CustomTagTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/joffzhang/spring/customtag/customtag.xml");
		User testBean = (User) applicationContext.getBean("testBean");
		System.out.println(testBean.getUserName() + " ========= "+testBean.getEmail());
	}
}
