package com.joffzhang.spring.bean.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

/**
 * bean循环依赖
 * 	1.构造器循环依赖
 * 	2.setter循环依赖
 * 	3.prototype范围的循环依赖
 * @author zy
 * @date 2020/7/15 14:45
 */
public class BeanCircularPrototype3 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanA.class,BeanB.class);
		System.out.println(applicationContext.getBean(BeanA.class));
		/*
		 *org.springframework.beans.factory.UnsatisfiedDependencyException:
		 * Error creating bean with name 'beanCircularPrototype3.BeanB': Unsatisfied dependency expressed through field 'beanA'; nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException:
		 * Error creating bean with name 'beanCircularPrototype3.BeanA': Requested bean is currently in creation: Is there an unresolvable circular reference?
		 */
	}
	@Scope("prototype")
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
	@Scope("prototype")
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
