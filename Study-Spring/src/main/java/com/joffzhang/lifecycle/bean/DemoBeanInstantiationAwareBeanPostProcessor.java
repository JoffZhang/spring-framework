package com.joffzhang.lifecycle.bean;

import com.joffzhang.PrintLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

/**
 * 一般情况下，当我们需要实现InstantiationAwareBeanPostProcessor接口时，是通过继承Spring框架中InstantiationAwareBeanPostProcessor接口实现类
 * InstantiationAwareBeanPostProcessorAdapter这个适配器类来简化我们实现接口的工作
 */
@Component
public class DemoBeanInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	public DemoBeanInstantiationAwareBeanPostProcessor() {
		PrintLog.printNote("【InstantiationAwareBeanPostProcessor接口】 调用 InstantiationAwareBeanPostProcessor的构造方法");
	}

	/**
	 * 实例化Bean之前 ,返回实例则不对bean实例化，返回null则进行spring bean实例化 doCreateBean
	 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation(java.lang.Class, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		PrintLog.printNote("【InstantiationAwareBeanPostProcessor接口】 调用 postProcessBeforeInstantiation(Class<?> beanClass, String beanName)");
		return super.postProcessBeforeInstantiation(beanClass, beanName);
	}
	/**
	 * 实例化Bean之后，在填充bean属性之前调用，返回true则进行下一步属性填充，返回false则不进行属性填充
	 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		PrintLog.printNote("【InstantiationAwareBeanPostProcessor接口】 调用 postProcessAfterInstantiation(Object bean, String beanName)");
		return super.postProcessAfterInstantiation(bean, beanName);
	}

	/**
	 * 属性赋值前调用，在applyPropertyValues之前操作可以对属性添加或修改等操作最后在通过applyPropertyValues应用bean对应的wapper对象
	 * @see org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessProperties(org.springframework.beans.PropertyValues, java.lang.Object, java.lang.String)
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		PrintLog.printNote("【InstantiationAwareBeanPostProcessor接口】 调用 postProcessProperties(PropertyValues pvs, Object bean, String beanName)");
		//return super.postProcessProperties(pvs, bean, beanName);
		return pvs;
	}
}
