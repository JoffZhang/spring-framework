package com.joffzhang.extension;

import com.joffzhang.PrintLog;
import com.joffzhang.annotation.bean.Blue;
import com.joffzhang.annotation.bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/1/26 13:41
 * {@link BeanDefinitionRegistryPostProcessor} 这个接口在读取项目中的beanDefinition之后执行。
 * BeanDefinitionRegistryPostProcessor继承自BeanFactoryPostProcessor，是一种比较特殊的BeanFactoryPostProcessor。
 * BeanDefinitionRegistryPostProcessor中定义的postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)方法 可以让我们实现自定义的注册bean定义的逻辑。
 * 使用场景： 在这动态注册自己的beanDefinition，可以加载classpath之外的bean
 */

public class Ext02_BeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	/**
	 * 在标准初始化之后，修改应用程序上下文的内部bean定义注册表。所有常规bean定义都将已加载，
	 * 但尚未实例化任何bean。这允许在下一个后处理阶段开始之前添加更多的bean定义。
	 * @param registry the bean definition registry used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("[02_BeanDefinitionRegistryPostProcessor] postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)");
		PrintLog.printNote("可在此动态注册或修改beanDefinition");
		//新增Bean定义
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Person.class);
		registry.registerBeanDefinition("person",rootBeanDefinition);
		PrintLog.printCase("添加person beanDefinition");
	}

	/**
	 * 在标准初始化之后，修改应用程序上下文的内部bean工厂。
	 * 所有bean定义都将被加载，但是还没有实例化bean。这样甚至可以覆盖或添加*属性，甚至可以用于初始化bean
	 * @param beanFactory the bean factory used by the application context
	 * @throws BeansException
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("[02_BeanDefinitionRegistryPostProcessor] postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)");
	}
}
