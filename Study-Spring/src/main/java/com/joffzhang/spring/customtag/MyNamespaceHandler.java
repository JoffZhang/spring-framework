package com.joffzhang.spring.customtag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author zy
 * @date 2020/7/23 13:19
 * 创建一个handler文件，扩展自NamespaceHandlerSupport，目的是将组件注册到spring容器中
 */
public class MyNamespaceHandler  extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
	}
}
