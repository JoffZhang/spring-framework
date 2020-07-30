package com.joffzhang.spring.bean.circular;

import com.joffzhang.spring.ioc.MainStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * bean循环依赖
 * 	1.构造器循环依赖
 * 	2.setter循环依赖
 * 	3.prototype范围的循环依赖
 * @author zy
 * @date 2020/7/15 14:45
 */
public class BeanCircularConstuctor1 {

	public static void main(String[] args) {
		try{
			ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanA.class,BeanB.class);
		}catch (Exception e){
			//在创建b时抛出异常；
			Throwable cause = e.getCause().getCause();
			e.printStackTrace();
			//org.springframework.beans.factory.UnsatisfiedDependencyException:
			// Error creating bean with name 'beanCircularTest1.BeanB': Unsatisfied dependency expressed through constructor parameter 0;
			// nested exception is org.springframework.beans.factory.BeanCurrentlyInCreationException:
			// Error creating bean with name 'beanCircularTest1.BeanA': Requested bean is currently in creation: Is there an unresolvable circular reference?
		}

	}

	public static class BeanA{
		private BeanB beanB;
		@Autowired
		public BeanA(BeanB beanB) {
			this.beanB = beanB;
		}
	}
	public static class BeanB{
		private BeanA beanA;
		@Autowired
		public BeanB(BeanA beanA) {
			this.beanA = beanA;
		}
	}


}
