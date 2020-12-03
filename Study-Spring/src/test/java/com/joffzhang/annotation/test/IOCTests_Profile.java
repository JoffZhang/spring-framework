package com.joffzhang.annotation.test;

import com.joffzhang.annotation.bean.Boss;
import com.joffzhang.annotation.bean.Car;
import com.joffzhang.annotation.config.MainConfigOfAutowired;
import com.joffzhang.annotation.config.MainConfigOfProfile;
import com.joffzhang.annotation.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author zy
 * @date 2020/12/1 9:34
 */
public class IOCTests_Profile extends BaseTest {
	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

	//1.使用命令行动态参数切换环境:在虚拟机参数位置添加 -ea -Dspring.profiles.active=prod
	@Test
	public void test01(){
		System.out.println("容器创建完成");
		//printBeans(annotationConfigApplicationContext);

		String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(DataSource.class);
		for (String s : beanNamesForType) {
			System.out.println(s);
		}
		Object blue = annotationConfigApplicationContext.getBean("blue");
		System.out.println("blue = "+blue);
		annotationConfigApplicationContext.close();
	}
	//2.代码方式激活环境
	@Test
	public void test02(){
		//1.创建applicationContext
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		//2.设置需要激活的环境
		applicationContext.getEnvironment().setActiveProfiles("prod");//"dev",
		//3.注册主配置类
		applicationContext.register(MainConfigOfProfile.class);
		//4.刷新容器
		applicationContext.refresh();

		System.out.println("容器创建完成");
		//printBeans(annotationConfigApplicationContext);

		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		for (String s : beanNamesForType) {
			System.out.println(s);
		}

		applicationContext.close();
	}
}
