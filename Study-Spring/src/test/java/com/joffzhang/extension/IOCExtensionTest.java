package com.joffzhang.extension;

import com.joffzhang.annotation.bean.Person;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 扩展点测试
 * @author zy
 * @date 2021/1/25 17:03
 */
public class IOCExtensionTest {

//	@Test
//	public void testAll(){
//		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainExtensionConfig.class);
//		printBeanDefinition(annotationConfigApplicationContext);
//		Person bean = annotationConfigApplicationContext.getBean("person",Person.class);
//		System.out.println(bean);
//	}

	@Test
	public void test02BeanDefinitionRegistryPostProsessor(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext02_BeanDefinitionRegistryPostProcessor.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test03BeanFactoryPostProcessor(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext03_BeanFactoryPostProcessor.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test04InstantiationAwareBeanPostProcessor(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext04_InstantiationAwareBeanPostProcessor.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test05BeanFactoryAware(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext05_BeanFactoryAware.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test07BeanNameAware(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext07_BeanNameAware.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void testPostConstructAnnotation(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PostConstructAnnotation.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test08InitialzingBean(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext08_InitializingBean.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test09FactoryBean(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext09_FactoryBean.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test10SmartInitializingSingleton(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext10_SmartInitializingSingleton.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test11DisposableBean(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext11_DisposableBean.class);
		printBeanDefinition(applicationContext);
	}

	@Test
	public void test12ApplicationListener(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Ext12_ApplicationListener.class);
		printBeanDefinition(applicationContext);
	}

	private void printBeanDefinition(AnnotationConfigApplicationContext annotationConfigApplicationContext){
		System.out.println("==========beanDefinitionNames=====================================================");
		String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}
}
