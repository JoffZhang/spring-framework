package com.joffzhang.extension;

import com.joffzhang.PrintLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * @author zy
 * @date 2021/1/26 16:03
 *
 * {@link InstantiationAwareBeanPostProcessor} 该接口继承了 {@link org.springframework.beans.factory.config.BeanPostProcessor} 接口
 * 区别：
 * 		BeanPostProcessor	接口只在bean的初始化阶段进行扩展（注入spring上下文前后）
 * 		InstantiationAwareBeanPostProcessor		接口在此基础上增加3个方法，把可扩展的范围增加了实例化阶段和属性注入阶段
 * 			该类扩展点有5个方法，主要在bean生命周期的两个大阶段：  实例化 和 初始化 阶段。
 * 			调用顺序为：
 * 				postProcessBeforeInstantiation	实例化bean前，相当于new个这个bean之前
 * 				postProcessAfterInstantiation	实例化bean之后，相当于new个这个bean之后
 * 							（5.1之后弃用）postProcessPropertyValues ：bean已经实例化完成，在属性注入阶段触发，@Autowired @Resource等注解原理基于此方法
 * 				postProcessProperties	在工厂将它们应用于给定的bean之前，对给定的属性值进行后处理，而无需任何属性描述符。
 * 				postProcessBeforeInitialization	初始化bean之前，相当于把bean注入spring上下文之前
 * 				postProcessAfterInitialization	初始化bean之后，相当于把bean注入spring上下文之后
 * 		使用场景：这个扩展点非常有用 ，无论是写中间件和业务中，都能利用这个特性。比如对实现了某一类接口的bean在各个生命期间进行收集，或者对某个类型的bean进行统一的设值等等
 *
 *	每个bean实例化和初始化前后都会触发调用
 */

public class Ext04_InstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		System.out.println("[04_InstantiationAwareBeanPostProcessor] postProcessBeforeInstantiation(Class<?> beanClass, String beanName)");
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("[04_InstantiationAwareBeanPostProcessor] postProcessAfterInstantiation(Object bean, String beanName)");

		return true;
	}

	/**
	 * 在工厂将它们*应用于给定的bean之前，对给定的属性值进行后处理，而无需任何属性描述符。
	 * <p>如果实现提供自定义的{{link #postProcessPropertyValues}实现，则实现应返回{@code null}（默认值），否则返回{@code pvs}。
	 * 在此接口的将来版本中（删除了{@link #postProcessPropertyValues}），
	 * 默认实现将直接按原样返回给定的{@code pvs}。
	 * @param pvs 		工厂将要应用的属性值（从不使用{@code null}）
	 * @param bean 		创建了bean实例，但尚未设置其属性
	 * @param beanName 	bean的名称
	 * @retrun 返回要应用于给定bean的实际属性值（可以是传入的* PropertyValues实例），或者返回{@code null}并继续使用现有属性，
	 * 但特别是继续调用{@link #postProcessPropertyValues} （需要为当前bean类初始化{@code PropertyDescriptor} s）
	 * @return
	 * @throws BeansException
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		System.out.println("[04_InstantiationAwareBeanPostProcessor] postProcessProperties(PropertyValues pvs, Object bean, String beanName)");

		return pvs;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("[04_InstantiationAwareBeanPostProcessor] postProcessBeforeInitialization(Object bean, String beanName)");

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("[04_InstantiationAwareBeanPostProcessor] postProcessAfterInitialization(Object bean, String beanName)");

		return bean;
	}

	//5.1之后弃用
//	@Override
//	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
//		return pvs;
//	}
}
