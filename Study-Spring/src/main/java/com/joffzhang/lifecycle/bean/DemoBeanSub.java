package com.joffzhang.lifecycle.bean;

import com.joffzhang.PrintLog;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

/**
 * @author zy
 * @date 2021/2/22 11:15
 */
public class DemoBeanSub extends DemoBean implements BeanClassLoaderAware, EnvironmentAware, EmbeddedValueResolverAware, ResourceLoaderAware, ApplicationEventPublisherAware, MessageSourceAware {

	private String demoBeanSubName;

	public String getDemoBeanSubName() {
		return demoBeanSubName;
	}

	public void setDemoBeanSubName(String demoBeanSubName) {
		PrintLog.printNote("【DemoBeanSub】 setDemoBeanSubName(String demoBeanSubName) --》 设置属性值demoBeanSubName");
		this.demoBeanSubName = demoBeanSubName;
	}

	/**
	 * @see  org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader(java.lang.ClassLoader)
	 * @param classLoader the owning class loader
	 */
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		PrintLog.printNote("【DemoBeanSub】 BeanClassLoaderAware#setBeanClassLoader(ClassLoader classLoader)");
	}

	/**
	 * @see org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher(org.springframework.context.ApplicationEventPublisher)
	 * @param applicationEventPublisher event publisher to be used by this object
	 */
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		PrintLog.printNote("【DemoBeanSub】 ApplicationEventPublisherAware#setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) ");
	}

	/**
	 * @see org.springframework.context.EmbeddedValueResolverAware#setEmbeddedValueResolver(org.springframework.util.StringValueResolver)
	 * @param resolver
	 */
	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		PrintLog.printNote("【DemoBeanSub】 EmbeddedValueResolverAware#setEmbeddedValueResolver(StringValueResolver resolver) ");
	}

	/**
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 * @param environment
	 */
	@Override
	public void setEnvironment(Environment environment) {
		PrintLog.printNote("【DemoBeanSub】 EnvironmentAware#setEnvironment(Environment environment) ");
	}

	/**
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(org.springframework.context.MessageSource)
	 * @param messageSource message source to be used by this object
	 */
	@Override
	public void setMessageSource(MessageSource messageSource) {
		PrintLog.printNote("【DemoBeanSub】 MessageSourceAware#setMessageSource(MessageSource messageSource) ");
	}

	/**
	 * @see org.springframework.context.ResourceLoaderAware#setResourceLoader(org.springframework.core.io.ResourceLoader)
	 * @param resourceLoader the ResourceLoader object to be used by this object
	 */
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		PrintLog.printNote("【DemoBeanSub】 ResourceLoaderAware#setResourceLoader(ResourceLoader resourceLoader) ");
	}

	@Override
	public String toString() {
		return "DemoBeanSub{" +
				"demoBeanSubName='" + demoBeanSubName + '\'' +
				"};" +super.toString();
	}
}
