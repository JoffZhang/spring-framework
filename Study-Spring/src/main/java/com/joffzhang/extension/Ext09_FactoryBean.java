package com.joffzhang.extension;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author zy
 * @date 2021/1/27 10:44
 * {@link FactoryBean} 一般情况下，Spring通过反射机制利用bean的class属性指定支线类去实例化bean，
 * 在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在bean中提供大量的配置信息。
 * 配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
 * Spring为此提供了一个org.springframework.bean.factory.FactoryBean的工厂类接口，用户可以通过实现该接口定制实例化Bean的逻辑。
 * FactoryBean接口对于Spring框架来说占用重要的地位，Spring自身就提供了70多个FactoryBean的实现。它们隐藏了实例化一些复杂bean的细节，给上层应用带来了便利。
 * 从Spring3.0开始，FactoryBean开始支持泛型，即接口声明改为FactoryBean<T>的形式
 */
public class Ext09_FactoryBean implements FactoryBean<Ext09_FactoryBean.Ext09_FactoryInnerBean> {

	@Override
	public Ext09_FactoryBean.Ext09_FactoryInnerBean getObject() throws Exception {
		return new Ext09_FactoryBean.Ext09_FactoryInnerBean();
	}

	@Override
	public Class<?> getObjectType() {
		return Ext09_FactoryBean.Ext09_FactoryInnerBean.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


	public static class Ext09_FactoryInnerBean{

	}
}
