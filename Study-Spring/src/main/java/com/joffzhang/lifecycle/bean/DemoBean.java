package com.joffzhang.lifecycle.bean;

import com.joffzhang.PrintLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Bean的生命周期
 * @author zy
 * @date 2021/2/3 11:26
 */
public class DemoBean implements InitializingBean , DisposableBean , BeanNameAware , BeanFactoryAware {

	private String name;
	private int age;
	//实现了BeanNameAware接口，Spring可以将BeanName注入该属性中
	private String beanName;
	//实现了BeanFactory接口，Spring可将BeanFactory注入该属性中
	private BeanFactory beanFactory;

	public DemoBean() {
		PrintLog.printNote("【Bean的构造方法】 DemoBean的构造方法");
	}

	public void demoInit(){
		PrintLog.printNote("【init-method】 调用 init-method 属性配置的初始化方法");
	}

	public void demoDestroy(){
		PrintLog.printNote("【destroy-method】 调用 destroy-method 属性配置的销毁方法");
	}

	@PostConstruct
	public void postConstruct(){
		PrintLog.printNote("【init-method】 调用 @PostConstruct 注释的初始化方法");
	}
	@PreDestroy
	public void preDestroy(){
		PrintLog.printNote("【destroy-method】 调用 @PreDestroy 注释的销毁方法");
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		PrintLog.printNote("【InitializingBean接口】 调用 InitializingBean#afterPropertiesSet()");
	}

	/**
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 * @throws BeansException
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		PrintLog.printNote("【BeanFactoryAware接口】 调用 BeanFactoryAware#setBeanFactory(BeanFactory beanFactory)--》得到beanFactory的引用");
	}

	/**
	 * @see org.springframework.beans.factory.BeanNameAware#setBeanName(java.lang.String)
	 */
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
		PrintLog.printNote("【BeanNameAware接口】 调用 BeanNameAware#setBeanName(String name)--》得到Bean的名称");
	}

	/**
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception {
		PrintLog.printNote("【DisposableBean接口】 调用DisposableBean#destroy()--》销毁操作");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		PrintLog.printNote("【set注入】 调用setName(String name)--》注入name");
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
		PrintLog.printNote("【set注入】 调用setAge(int age)--》注入age");
	}

	@Override
	public String toString() {
		return "DemoBean{" +
				"name='" + name + '\'' +
				", age=" + age +
				", beanName='" + beanName + '\'' +
				", beanFactory=" + beanFactory +
				'}';
	}
}
