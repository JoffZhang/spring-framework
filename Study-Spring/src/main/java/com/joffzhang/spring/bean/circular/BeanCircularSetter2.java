package com.joffzhang.spring.bean.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * bean循环依赖
 * 	1.构造器循环依赖
 * 	2.setter循环依赖
 * 	3.prototype范围的循环依赖
 * @author zy
 * @date 2020/7/15 14:45
 */
public class BeanCircularSetter2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanA.class,BeanB.class);
	}

	public static class BeanA{
		@Autowired
		private BeanB beanB;

		public BeanB getBeanB() {
			return beanB;
		}

		public void setBeanB(BeanB beanB) {
			this.beanB = beanB;
		}
	}
	public static class BeanB{
		@Autowired
		private BeanA beanA;

		public BeanA getBeanA() {
			return beanA;
		}

		public void setBeanA(BeanA beanA) {
			this.beanA = beanA;
		}
	}


}
