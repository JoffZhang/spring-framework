package com.joffzhang.service;

import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2020/12/9 17:58
 */
@Service
public class HelloService {

	public String sayHello(String name){
		return name;
	}
}
