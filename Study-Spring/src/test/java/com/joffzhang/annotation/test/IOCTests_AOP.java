package com.joffzhang.annotation.test;

import com.joffzhang.annotation.aop.MathCalculator;
import com.joffzhang.annotation.config.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author zy
 * @date 2020/12/1 9:34
 */
public class IOCTests_AOP extends BaseTest {
	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);


	@Test
	public void test01(){
		System.out.println("容器创建完成");

		MathCalculator bean = annotationConfigApplicationContext.getBean(MathCalculator.class);
		int div = bean.div(1, 1);
		System.out.println("计算结果 ："+div);

		annotationConfigApplicationContext.close();
	}

}
