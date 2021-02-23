package com.joffzhang.lifecycle;

import com.joffzhang.PrintLog;
import com.joffzhang.lifecycle.bean.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring Bean的生命周期
 */
public class SpringBeanLifeCycle {


	@Test
	public void testBeanLifeCycle(){
		PrintLog.printMark("初始化Spring容器-start","new AnnotationConfigApplicationContext()");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		//只追踪DemoBean的 实例化、初始化过程
		BeanDefinition demoBeanDefinition = new RootBeanDefinition(DemoBean.class);
		demoBeanDefinition.setInitMethodName("demoInit");
		demoBeanDefinition.setDestroyMethodName("demoDestroy");
		demoBeanDefinition.getPropertyValues().addPropertyValue("name","初始化name属性");
		demoBeanDefinition.getPropertyValues().addPropertyValue("age","1");
		applicationContext.registerBeanDefinition("demoBean",demoBeanDefinition);

		applicationContext.refresh();
		PrintLog.printMark("初始化Spring容器-end","new AnnotationConfigApplicationContext()");

		//得到DemoBean，并打印信息
		DemoBean demoBean = applicationContext.getBean("demoBean", DemoBean.class);
		PrintLog.printCase(demoBean.toString());
		PrintLog.printMark("销毁Spring容器","applicationContext.registerShutdownHook();");
		/**
		 * 异步调用，log4j也会注册自己的关闭钩子，如logManager先关闭，则关闭相关日志不一定会打印。
		 * 可以配置log4j   shutdownHook="disable"
		 */
		applicationContext.registerShutdownHook();

	}

	@Test
	public void testBeanLifeCycleDemoBean(){
		PrintLog.printMark("初始化Spring容器-start","new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class)");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);
		PrintLog.printMark("初始化Spring容器-end","new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class)");

		//得到DemoBean，并打印信息
		DemoBean demoBean = applicationContext.getBean("demoBean", DemoBean.class);
		PrintLog.printCase(demoBean.toString());
		PrintLog.printMark("销毁Spring容器","applicationContext.registerShutdownHook();");
		/**
		 * 异步调用，log4j也会注册自己的关闭钩子，如logManager先关闭，则关闭相关日志不一定会打印。
		 * 可以配置log4j   shutdownHook="disable"
		 */
		applicationContext.registerShutdownHook();
	}

	@Test
	public void testBeanLifeCycleDemoBeanSub(){
		PrintLog.printMark("初始化Spring容器-start","new AnnotationConfigApplicationContext()");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		//只追踪DemoBean的 实例化、初始化过程
		BeanDefinition demoBeanSubDefinition = new RootBeanDefinition(DemoBeanSub.class);
		demoBeanSubDefinition.setInitMethodName("demoInit");
		demoBeanSubDefinition.setDestroyMethodName("demoDestroy");
		demoBeanSubDefinition.getPropertyValues().addPropertyValue("demoBeanSubName","初始化demoBeanSubName属性");
		applicationContext.registerBeanDefinition("demoBeanSub",demoBeanSubDefinition);

		applicationContext.register(DemoBeanInstantiationAwareBeanPostProcessor.class);
		applicationContext.register(DemoBeanPostProcessor.class);

		applicationContext.refresh();
		PrintLog.printMark("初始化Spring容器-end","new AnnotationConfigApplicationContext()");

		//得到DemoBeanSub，并打印信息
		DemoBeanSub demoBeanSub = applicationContext.getBean("demoBeanSub", DemoBeanSub.class);
		PrintLog.printCase(demoBeanSub.toString());
		PrintLog.printMark("销毁Spring容器","applicationContext.registerShutdownHook();");
		/**
		 * 异步调用，log4j也会注册自己的关闭钩子，如logManager先关闭，则关闭相关日志不一定会打印。
		 * 可以配置log4j   shutdownHook="disable"
		 */
		applicationContext.registerShutdownHook();
	}

	@Test
	public void testBeanLifeCycleAll(){
		PrintLog.printMark("初始化Spring容器-start","new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class)");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class);
		PrintLog.printMark("初始化Spring容器-end","new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class)");

		//得到DemoBean，并打印信息
		DemoBean demoBean = applicationContext.getBean("demoBean", DemoBean.class);
		PrintLog.printCase(demoBean.toString());
		PrintLog.printMark("销毁Spring容器","applicationContext.registerShutdownHook();");
		/**
		 * 异步调用，log4j也会注册自己的关闭钩子，如logManager先关闭，则关闭相关日志不一定会打印。
		 * 可以配置log4j   shutdownHook="disable"
		 */
		applicationContext.registerShutdownHook();
	}

	/*
18:00:43,990 INFO  [main] *.printMark(22):  【初始化Spring容器-start】 new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class)
18:00:45,456 TRACE [main] *.printNote(31):   |------> 【BeanFactoryPostProcessor接口】 调用 BeanFactoryPostProcessor的构造方法
18:00:45,457 TRACE [main] *.printNote(31):   |------> 【BeanFactoryPostProcessor接口】 调用 postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
18:00:45,493 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 InstantiationAwareBeanPostProcessor的构造方法
18:00:45,502 TRACE [main] *.printNote(31):   |------> 【BeanPostProcessor接口】 调用BeanPostProcessor的构造方法
18:00:45,512 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
18:00:45,513 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 postProcessAfterInstantiation(Object bean, String beanName)
18:00:45,513 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 postProcessProperties(PropertyValues pvs, Object bean, String beanName)
18:00:45,514 TRACE [main] *.printNote(31):   |------> 【BeanPostProcessor接口】 调用 postProcessBeforeInitialization(Object bean, String beanName),这里可以对beanLifeCycleConfig属性进行修改
18:00:45,514 TRACE [main] *.printNote(31):   |------> 【BeanPostProcessor接口】 调用 postProcessAfterInitialization(Object bean, String beanName),这里可以对beanLifeCycleConfig属性进行修改
18:00:45,514 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
18:00:45,532 TRACE [main] *.printNote(31):   |------> 【Bean的构造方法】 DemoBean的构造方法
18:00:45,533 TRACE [main] *.printNote(31):   |------> 【set注入】 调用setName(String name)--》注入name
18:00:45,533 TRACE [main] *.printNote(31):   |------> 【set注入】 调用setAge(int age)--》注入age
18:00:45,537 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 postProcessAfterInstantiation(Object bean, String beanName)
18:00:45,539 TRACE [main] *.printNote(31):   |------> 【InstantiationAwareBeanPostProcessor接口】 调用 postProcessProperties(PropertyValues pvs, Object bean, String beanName)
18:00:45,540 TRACE [main] *.printNote(31):   |------> 【BeanNameAware接口】 调用 BeanNameAware#setBeanName(String name)--》得到Bean的名称
18:00:45,540 TRACE [main] *.printNote(31):   |------> 【BeanFactoryAware接口】 调用 BeanFactoryAware#setBeanFactory(BeanFactory beanFactory)--》得到beanFactory的引用
18:00:45,541 TRACE [main] *.printNote(31):   |------> 【BeanPostProcessor接口】 调用 postProcessBeforeInitialization(Object bean, String beanName),这里可以对demoBean属性进行修改
18:00:45,541 TRACE [main] *.printNote(31):   |------> 【init-method】 调用 @PostConstruct 注释的初始化方法
18:00:45,542 TRACE [main] *.printNote(31):   |------> 【InitializingBean接口】 调用 InitializingBean#afterPropertiesSet()
18:00:45,543 TRACE [main] *.printNote(31):   |------> 【init-method】 调用 init-method 属性配置的初始化方法
18:00:45,543 TRACE [main] *.printNote(31):   |------> 【BeanPostProcessor接口】 调用 postProcessAfterInitialization(Object bean, String beanName),这里可以对demoBean属性进行修改
18:00:45,591 INFO  [main] *.printMark(22):  【初始化Spring容器-end】 new AnnotationConfigApplicationContext(BeanLifeCycleConfig.class)
18:00:45,592 DEBUG [main] *.printCase(39):    |**  case  **  DemoBean{name='初始化name = DEMO_BEAN', age=1, beanName='demoBean', beanFactory=org.springframework.beans.factory.support.DefaultListableBeanFactory@618c5d94: defining beans [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,org.springframework.context.event.internalEventListenerProcessor,org.springframework.context.event.internalEventListenerFactory,beanLifeCycleConfig,demoBeanFactoryPostProcessor,demoBeanInstantiationAwareBeanPostProcessor,demoBeanPostProcessor,demoBean]; root of factory hierarchy}
18:00:45,592 INFO  [main] *.printMark(22):  【销毁Spring容器】 applicationContext.registerShutdownHook();

18:00:45,611 TRACE [SpringContextShutdownHook] *.printNote(31):   |------> 【destroy-method】 调用 @PreDestroy 注释的销毁方法
18:00:45,611 TRACE [SpringContextShutdownHook] *.printNote(31):   |------> 【DisposableBean接口】 调用DisposableBean#destroy()--》销毁操作
18:00:45,612 TRACE [SpringContextShutdownHook] *.printNote(31):   |------> 【destroy-method】 调用 destroy-method 属性配置的销毁方法
Disconnected from the target VM, address: '127.0.0.1:51688', transport: 'socket'

Process finished with exit code 0
	 */
}
