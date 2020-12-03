package com.joffzhang.annotation.bean;

import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2020/12/1 9:30
 */
@Component
public class Car {

	public Car(){
		System.out.println("car constructor");
	}


	public void init(){
		System.out.println("car init");
	}

	public void destroy(){
		System.out.println("car destroy");
	}
}
