package com.joffzhang.simple;

import com.joffzhang.annotation.bean.Person;
import com.joffzhang.annotation.config.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zy
 * @date 2021/1/25 17:03
 */
public class IOCSimpleTest {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainSimpleConfig.class);
		System.out.println("==========beanDefinitionNames=====================================================");
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}

	}
}
