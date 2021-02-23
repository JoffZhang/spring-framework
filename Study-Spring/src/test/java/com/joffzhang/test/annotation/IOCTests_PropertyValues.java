package com.joffzhang.test.annotation;

import com.joffzhang.annotation.config.MainConfigOfPropertyValues;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author zy
 * @date 2020/12/1 9:34
 */
public class IOCTests_PropertyValues extends BaseTest {
	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

	@Test
	public void test01(){
		System.out.println("容器创建完成");
		printBeans(annotationConfigApplicationContext);

		Object person = annotationConfigApplicationContext.getBean("person");
		System.out.println(person);

		ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
		String property = environment.getProperty("person.nickName");
		System.out.println(property);
		annotationConfigApplicationContext.close();
	}


}
