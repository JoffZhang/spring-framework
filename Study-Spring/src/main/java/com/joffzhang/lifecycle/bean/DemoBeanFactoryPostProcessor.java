package com.joffzhang.lifecycle.bean;

import com.joffzhang.PrintLog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/2/3 17:33
 */
@Component
public class DemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public DemoBeanFactoryPostProcessor() {
		PrintLog.printNote("【BeanFactoryPostProcessor接口】 调用 BeanFactoryPostProcessor的构造方法");
	}

	/**
	 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
	 *
	 * 可通过postProcessBeanFactory对beanFactory进行设置
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		PrintLog.printNote("【BeanFactoryPostProcessor接口】 调用 postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)");
		BeanDefinition demoBeanDefinition = beanFactory.getBeanDefinition("demoBean");
		demoBeanDefinition.getPropertyValues().addPropertyValue("age",21);
	}
}
