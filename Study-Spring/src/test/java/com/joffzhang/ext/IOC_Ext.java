package com.joffzhang.ext;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zy
 * @date 2020/12/7 9:40
 */
public class IOC_Ext {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfExt.class);
		System.out.println("容器初始化完成");
		//发布事件
		applicationContext.publishEvent(new ApplicationEvent(new String("自定义发布事件")) {
		});
		applicationContext.close();

	}
}
