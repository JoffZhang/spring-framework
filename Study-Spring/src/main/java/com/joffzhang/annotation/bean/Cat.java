package com.joffzhang.annotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2020/12/1 9:46
 */
@Component
public class Cat implements InitializingBean, DisposableBean {


	public Cat(){
		System.out.println(" cat construction ");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println(" cat destroy ");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(" cat afterPropertiesSet ");
	}
}
