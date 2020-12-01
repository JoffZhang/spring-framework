package com.joffzhang.annotation.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zy
 * @date 2020/11/30 15:43
 * 根据系统判断 是注册linux 还是 windows
 */
public class MyCondition {

	//判断linux系统
	public static class LinuxCondition implements Condition{
		/**
		 * @param context 判断条件能使用的上下文环境
		 * @param metadata 注释信息
		 */
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			//1.根据条件上下文环境 获取到ioc使用的beanFactory
			ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
			//2.获取到类加载器
			ClassLoader classLoader = context.getClassLoader();
			//3.获取当前环境信息
			Environment environment = context.getEnvironment();
			//4.获取bean定义的注册类
			BeanDefinitionRegistry registry = context.getRegistry();
			ResourceLoader resourceLoader = context.getResourceLoader();
			//可以判断容器中的bean的注册情况，也可以给容器中注册bean
			boolean person = registry.containsBeanDefinition("person");
			//判断操作系统
			String property = environment.getProperty("os.name");
			if(property.contains("linux")){
				return true;
			}
			return false;
		}
	}
	//判断windows
	public static class WindowsCondition implements Condition{
		/**
		 * @param context 判断条件能使用的上下文环境
		 * @param metadata 注释信息
		 */
		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			Environment environment = context.getEnvironment();
			String property = environment.getProperty("os.name");
			if(property.contains("Windows")){
				return true;
			}
			return false;
		}
	}
}
