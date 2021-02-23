package com.joffzhang.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * @author zy
 * @date 2021/1/26 17:26
 *
 * {@link SmartInstantiationAwareBeanPostProcessor}	该接口继承自 InstantiationAwareBeanPostProcessor
 * 该扩展有3个触发点：
 * 		predictBeanType	：该触发点在postProcessBeforeInstantiation之前，这个方法用于预测Bean的类型，返回一个预测成功的Class类型，如果不能预测返回null；
 * 			当调用BeanFactory.getType(name)时当通过bean的名字无法获取bean类型信息时就调用该方法来决定类型信息
 * 		determineCandidateConstructors	：该触发点在postProcessorBeforeInstantiation之后，用于确定该bean的构造函数之用，返回的是该bean的所有构造函数列表。
 * 			用户可以扩展这个点，来自定义选择相应的构造器来实例化这个bean
 * 		getEarlyBeanReference	：该触发点发生在postProcessAfterInstantiation之后，当有循环依赖的场景，当bean实例化好之后，为了防止有循环依赖，会提前暴露回调方法，
 * 			用于bean实例化的后置处理。这个方法就是在提前暴露的回调方法中触发。
 */
public class Ext000_SmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("[000_SmartInstantiationAwareBeanPostProcessor] predictBeanType(Class<?> beanClass, String beanName)");
		return beanClass;
	}

	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("[000_SmartInstantiationAwareBeanPostProcessor] determineCandidateConstructors(Class<?> beanClass, String beanName)");
		return null;
	}

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		System.out.println("[000_SmartInstantiationAwareBeanPostProcessor] getEarlyBeanReference(Object bean, String beanName)");
		return bean;
	}
}
