package com.joffzhang.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理启：初始化前后进行处理工作
 * @author zy
 * @date 2020/12/1 10:02
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor  postProcessBeforeInitialization  [" + beanName +"] > "+bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("MyBeanPostProcessor  postProcessAfterInitialization  [" + beanName +"] > "+bean);
		return bean;
	}
}
