package com.joffzhang.spring.customtag;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @author zy
 * @date 2020/7/23 13:14
 * 创建一个实现BeanDefinitionParser接口，用来解析xsd文件中的定义和组件定义
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	//Element对应的类
	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	//从element中解析并提取相应的元素
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder bdBuilder) {
		String userName = element.getAttribute("userName");
		String email = element.getAttribute("email");
		//将提取的数据放入到BeanDefinitionBuilder中，待到完成所有bean的解析后统一注册到beanFactory中
		if(StringUtils.hasText(userName)){
			bdBuilder.addPropertyValue("userName",userName);
		}
		if(StringUtils.hasText(email)){
			bdBuilder.addPropertyValue("email",email);
		}
	}
}
