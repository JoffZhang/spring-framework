package com.joffzhang.extension;

import com.joffzhang.PrintLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author zy
 * @date 2021/1/26 15:15
 * {@link BeanFactoryPostProcessor} 为BeanFactory的扩展接口，spring容器初始化时，从资源中读取到bean的相关定义后，保存在beanFactory的成员变量中
 * （参考DefaultListableBeanFactory类的成员变量beanDefinitionMap），在实例化bean的操作就是依据这些bean的定义来做的，
 * 而在实例化之前，spring允许我们通过自定义扩展来改变bean的定义，定义一旦变了，后面的实例也就变了，而beanFactory后置处理器，即BeanFactoryPostProcessor就是用来改变bean定义的；
 *
 * 调用时机：spring读取beanDefinition信息之后，实例化bean之前
 * 可以通过实现这个扩展接口来自行处理逻辑，如修改已经注册的beanDefinition的元信息
 *
 */

public class Ext03_BeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	/**
	 * 在标准初始化之后，修改应用程序上下文的内部bean工厂。
	 * 所有bean定义都将被加载，但是还没有实例化bean。这样甚至可以覆盖或添加*属性，甚至可以用于初始化bean
	 * @param beanFactory the bean factory used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("[03_BeanFactoryPostProcessor] postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)");
		PrintLog.printNote("可在此修改已注册的beanDefinition");
		//打印当前容器内的BeanDefinition
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		//Arrays.stream(beanDefinitionNames).forEach(System.out::println);
		for (String beanDefinitionName : beanDefinitionNames) {
			if("person".equals(beanDefinitionName)){
				BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
				MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
				propertyValues.add("name","李四");
				PrintLog.printCase("修改person BeanDefinition name属性初始值");
			}
		}
	}
}
