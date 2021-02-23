package com.joffzhang.test.annotation;

import com.joffzhang.tx.LogService;
import com.joffzhang.tx.MainConfigOfTX;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author zy
 * @date 2020/12/1 9:34
 */
public class IOCTests_TX extends BaseTest {
	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfTX.class);

	@Test
	public void test01(){
		System.out.println("容器创建完成");

		LogService logService = annotationConfigApplicationContext.getBean(LogService.class);
		logService.insert();

		annotationConfigApplicationContext.close();
	}

}
