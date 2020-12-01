package com.joffzhang.annotation.config;

import com.joffzhang.annotation.bean.Color;
import com.joffzhang.annotation.bean.ColorFactoryBean;
import com.joffzhang.annotation.bean.Person;
import com.joffzhang.annotation.service.TestService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;

/**
 *
 * @Scope
 * @Lazy
 * @Conditional
 * @Import
 */
//类中组件统一设置，满足当前条件，这个类中配置的所有bean注册才能生效
@Conditional(MyCondition.WindowsCondition.class)
//配置类==配置文件
@Configuration	//告诉spring这是一个配置类
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})	//导入组件，id默认是组件的全类名
public class MainConfig2 {
	/**
	 *
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
	 * 	String SCOPE_SINGLETON = "singleton";	单实例 默认
	 * 	String SCOPE_PROTOTYPE = "prototype";	多实例
	 * 	String SCOPE_REQUEST = "request";	同一次请求创建一个实例
	 * 	String SCOPE_SESSION = "session";	同一个session创建一个实例
	 * 	String SCOPE_APPLICATION = "application";	全局web应用程序范围标识符:“application”。支持除了标准范围“单例”和“原型”。
	 *
	 * 	懒加载：
	 * 		单实例bean，默认是在容器启动的时候创建对象
	 * 		懒加载，容器启动时不创建对象，第一次使用（获取）bean的时候创建
	 */

	//singleton	单实例（默认），ioc容器启动会调用方法创建对象放到IOC容器中。以后每次获取直接从容器（map.get()）拿
	//@Scope
	@Lazy
	//给容器中注册一个Bean，类型为返回值的类型，id默认是方法名
	@Bean
	public Person personSingleton(){
		System.out.println("给容器中添加Person Singleton");
		return new Person("shuaige",13);
	}
	//prototype	多实例	ioc容器启动并不会调用方法创建对象放到容器中，每次获取的时候才会调用方法创建对象
	@Scope("prototype")
	//给容器中注册一个Bean，类型为返回值的类型，id默认是方法名
	@Bean
	public Person personPrototype(){
		System.out.println("给容器中添加Person Prototype");
		return new Person(" double shuaige",13);
	}

	/**
	 * @Conditional({Condition})	按照条件注册bean
	 *
	 */
	@Conditional(MyCondition.WindowsCondition.class)
	@Bean("bill")
	public Person persion01(){
		return new Person("bill Gates",62);
	}
	@Conditional(MyCondition.LinuxCondition.class)
	@Bean("linus")
	public Person persion02(){
		return new Person("linus ",61);
	}

	@Bean
	public ColorFactoryBean colorFactoryBean(){
		return new ColorFactoryBean();
	}
}
