package com.joffzhang.annotation.config;

import com.joffzhang.annotation.bean.Blue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zy
 * @date 2020/11/30 17:40
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 *
	 * @param importingClassMetadata 当前类的注解信息
	 * @param registry BeanDefinition注册类
	 *                 把所有需要添加到容器中的bean 调用BeanDefinitionRegistry.registerBeanDefinitions手动注册进去
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		boolean color = registry.containsBeanDefinition("com.joffzhang.annotation.bean.Color");
		if(color){//如果有color  bean,注册 blue bean
			//制定bean
			//指定bean定义信息，（Bean的类型，Bean...)
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Blue.class);
			//注册一个Bean，指定bean名称
			registry.registerBeanDefinition("blue",rootBeanDefinition);
		}
	}
}
