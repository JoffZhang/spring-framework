package com.joffzhang.annotation.service;

import com.joffzhang.annotation.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author zy
 * @date 2020/11/30 11:04
 */
@Service
public class TestService {

	//@Qualifier("testDao")
	//@Autowired(required=false)
	//@Resource(name="testDao2")
	@Inject
	private TestDao testDao;

	public void print(){
		System.out.println("TestService testDao = " + testDao);
	}


	@Override
	public String toString() {
		return "TestService{" +
				"testDao=" + testDao +
				'}';
	}
}
