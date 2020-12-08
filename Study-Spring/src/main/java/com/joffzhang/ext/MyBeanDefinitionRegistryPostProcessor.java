package com.joffzhang.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2020/12/7 10:09
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	//BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor .. postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) bean的数量"+registry.getBeanDefinitionCount());
		//自己添加BeanDefinition信息
		//RootBeanDefinition beanDefinition = new RootBeanDefinition(ObjectBean.class);
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(ObjectBean.class).getBeanDefinition();
		registry.registerBeanDefinition("自定义BeanDefinition信息",beanDefinition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionRegistryPostProcessor .. postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) bean的数量"+beanFactory.getBeanDefinitionCount());
	}
}
