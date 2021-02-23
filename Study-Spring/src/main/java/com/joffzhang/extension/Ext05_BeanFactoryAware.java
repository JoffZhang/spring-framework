package com.joffzhang.extension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/1/26 17:50
 *
 * {@link BeanFactoryAware} 触发点，发生在bean的实例化之后，注入属性之前，也就是setter之前。
 *	可拿到BeanFactory属性
 *
 *	使用场景：在bean实例化之后，但未初始化之前，拿到BeanFactory，可对每个bean做特殊化的定制。或者可以把BeanFactory进行缓存，以后使用
 */

public class Ext05_BeanFactoryAware implements BeanFactoryAware {
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("[05_BeanFactoryAware] setBeanFactory(BeanFactory beanFactory)");
	}
}
