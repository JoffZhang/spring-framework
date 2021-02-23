package com.joffzhang.test.annotation;

import com.joffzhang.annotation.bean.Boss;
import com.joffzhang.annotation.bean.Car;
import com.joffzhang.annotation.config.MainConfigOfAutowired;
import com.joffzhang.annotation.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		System.out.println(testService);
		//TestDao testDao = annotationConfigApplicationContext.getBean(TestDao.class);
		//System.out.println(testDao);

		Boss boss = annotationConfigApplicationContext.getBean(Boss.class);
		System.out.println(boss);

		Car car = annotationConfigApplicationContext.getBean(Car.class);
		System.out.println(car);
		annotationConfigApplicationContext.close();
	}


}
