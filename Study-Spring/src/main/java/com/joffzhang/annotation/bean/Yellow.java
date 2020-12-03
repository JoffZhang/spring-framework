package com.joffzhang.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author zy
 * @date 2020/11/30 17:29
 */
@Component
public class Yellow implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		System.out.println("ApplicationContextAware#setApplicationContext 传入IOC："+applicationContext);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware#setBeanName 当前bean的名字："+name);
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String s = resolver.resolveStringValue("你好${os.name} ,值 #{20*18}");
		System.out.println("EmbeddedValueResolverAware#setEmbeddedValueResolver 解析字符串："+s);
	}
}
