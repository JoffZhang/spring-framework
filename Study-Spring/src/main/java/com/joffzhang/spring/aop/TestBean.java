package com.joffzhang.spring.aop;

/**
 * @author zy
 * @date 2020/7/28 11:27
 * 1。创建用于拦截的bean
 */
public class TestBean {

	private String testStr="testStr";

	public void test(){
		System.out.println("test method");
	}

	public String getTestStr() {
		return testStr;
	}

	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}
}
