package com.joffzhang.annotation.config;

import com.joffzhang.annotation.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * bean的生命周期
 * 		bean创建---初始化---销毁的过程
 * 	容器管理bean的生命周期
 * 	可自定义初始化和销毁方法：容器在bean进行到当前声明周期的时候来调用自定义的初始化和销毁方法
 *
 *
 * 构造（对象创建）
 * 		单实例：容器启动时创建
 * 		多实例：每次获取对象时创建
 *
 *	BeanPostProcessor.postProcessorBeforeInitialization
 * 	初始化：	对象创建完成，并赋值好，调用初始化方法
 * 	BeanPostProcessor.postProcessorAfterInitialization
 *	销毁：	单实例：容器关闭时
 *			多实例：容器不会管理这个bean 容器不会自动调用销毁
 *
 * 遍历得到容器中所有的BeanPostProcessor，挨个执行beforeInitialization.
 * 		返回null，跳出循环，不会执行后面的BeanPostProcessor.postProcessBeforeInitialization
 *
 * BeanPostProcessor原理：
 * populateBean(beanName, mbd, instanceWrapper)	给bean进行属性赋值
 * initializeBean(beanName, exposedObject, mbd){
 * 		applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)
 * 		invokeInitMethods(beanName, wrappedBean, mbd)	//执行自定义初始化
 * 		applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)
 * }
 *
 *
 * 	1.指定初始化和销毁方法
 * 		@Bean(initMethod = "init",destroyMethod = "destroy")
 *	2.通过让bean实现	InitializingBean（定义初始化逻辑）afterPropertiesSet
 *					DisposableBean	(定义销毁逻辑)	destroy
 *	3.使用JSR250
 * 		@PostConstruct	:bean创建完成并且属性赋值完成，完成初始化逻辑
 * 		@PreDestroy		:在容器销毁bean之前通知我们进行销毁
 *	4.BeanPostProcessor [interface] : bean 后置处理器
 *		在bean初始化前后进行一些处理
 *		postProcessorBeforeInitialization:在初始化之前工作
 *		postProcessorAfterInitialization：在初始化之后工作
 *
 *spring底层对BeanPostProcessor的使用：
 * 	bean赋值，注入其他组件，@Autowired ，生命周期注解功能，@Async，。。 BeanPostProcessor
 * @author zy
 * @date 2020/12/1 9:28
 */
@ComponentScan("com.joffzhang.annotation.bean")
@Configuration
public class MainConfigOfLifeCycle {
	//@Scope("prototype")
	@Bean(initMethod = "init",destroyMethod = "destroy")
	public Car car(){
		return new Car();
	}
}
