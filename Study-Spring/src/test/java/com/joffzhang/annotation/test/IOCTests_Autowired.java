package com.joffzhang.annotation.test;

import com.joffzhang.annotation.config.MainConfigOfAutowired;
import com.joffzhang.annotation.config.MainConfigOfPropertyValues;
import com.joffzhang.annotation.dao.TestDao;
import com.joffzhang.annotation.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author zy
 * @date 2020/12/1 9:34
 */
public class IOCTests_Autowired extends BaseTest {
	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

	@Test
	public void test01(){
		System.out.println("容器创建完成");
		printBeans(annotationConfigApplicationContext);

		TestService testService = annotationConfigApplicationContext.getBean(TestService.class);
		testService.print();
		//TestDao testDao = annotationConfigApplicationContext.getBean(TestDao.class);

		annotationConfigApplicationContext.close();
	}


}
