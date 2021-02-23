package com.joffzhang.lifecycle.bean;

import com.joffzhang.PrintLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/2/3 17:11
 */
@Component
public class DemoBeanPostProcessor implements BeanPostProcessor {

	public DemoBeanPostProcessor() {
		PrintLog.printNote("【BeanPostProcessor接口】 调用BeanPostProcessor的构造方法");
	}

	/**
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		PrintLog.printNote("【BeanPostProcessor接口】 调用 postProcessBeforeInitialization(Object bean, String beanName),这里可以对"+beanName+"属性进行修改");
		return bean;
	}
	/**
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		PrintLog.printNote("【BeanPostProcessor接口】 调用 postProcessAfterInitialization(Object bean, String beanName),这里可以对"+beanName+"属性进行修改");
		return bean;
	}
}
