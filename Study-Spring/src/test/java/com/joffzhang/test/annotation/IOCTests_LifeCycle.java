package com.joffzhang.test.annotation;

import com.joffzhang.annotation.bean.Car;
import com.joffzhang.annotation.config.MainConfigOfLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zy
 * @date 2020/12/1 9:34
 */
public class IOCTests_LifeCycle {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
		System.out.println("容器创建完成");
		//annotationConfigApplicationContext.getBean(Car.class);
		annotationConfigApplicationContext.close();
	}
}
