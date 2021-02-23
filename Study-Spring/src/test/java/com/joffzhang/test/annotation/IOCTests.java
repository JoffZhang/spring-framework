package com.joffzhang.test.annotation;

import com.joffzhang.annotation.bean.Person;
import com.joffzhang.annotation.config.MainConfig;
import com.joffzhang.annotation.config.MainConfig2;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author zy
 * @date 2020/11/30 11:06
 */
public class IOCTests {


	@Test
	public void testImport(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		printBeans(annotationConfigApplicationContext);

		//工厂Bean获取的是调用getObject创建的对象
		Object colorFactoryBean = annotationConfigApplicationContext.getBean("colorFactoryBean");
		Object colorFactoryBean2 = annotationConfigApplicationContext.getBean("colorFactoryBean");
		System.out.println("bean的类型 = "+colorFactoryBean.getClass());
		System.out.println(colorFactoryBean == colorFactoryBean2);
		Object colorFactoryBean3 = annotationConfigApplicationContext.getBean("&colorFactoryBean");
		System.out.println("bean的类型 = "+colorFactoryBean3.getClass());


	}

	private void printBeans(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

	@Test
	public void test3(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
		System.out.println("ioc容器创建完成 evironment "+environment.getProperty("os.name"));//os.name -> Windows 10     修改启动参数测试  -Dos.name=linux
		String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(Person.class);
		for (String name : beanNamesForType) {
			System.out.println("beanName "+ name);
		}
		Map<String, Person> persons = annotationConfigApplicationContext.getBeansOfType(Person.class);
		System.out.println(persons);
	}

	/**
	 * @Scope
	 * 作用域
	 */
	@Test
	public void test02(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
//		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println(beanDefinitionName);
//		}
		System.out.println("ioc容器创建完成");
		//@Lazy 懒加载 单实例在获取使用时才创建
		annotationConfigApplicationContext.getBean("personSingleton");

		Object personPrototype = annotationConfigApplicationContext.getBean("personPrototype");
		Object personPrototype2 = annotationConfigApplicationContext.getBean("personPrototype");
		System.out.println("personPrototype == personPrototype2 ," + personPrototype == personPrototype2);
	}

	/**
	 * @ComponentScan
	 * 包扫描
	 */
	@Test
	public void test01(){
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

}
