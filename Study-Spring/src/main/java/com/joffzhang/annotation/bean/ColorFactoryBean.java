package com.joffzhang.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author zy
 * @date 2020/11/30 17:54
 */
public class ColorFactoryBean implements FactoryBean<Object> {
	//返回一个color对象，这个对象会添加到容器中
	@Override
	public Object getObject() throws Exception {
		return new Color();
	}

	@Override
	public Class<?> getObjectType() {
		return Color.class;
	}

	/**
	 * @return 此方法返回false不一定表示返回的对象是独立的实例
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
