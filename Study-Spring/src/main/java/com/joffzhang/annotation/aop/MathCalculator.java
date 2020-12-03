package com.joffzhang.annotation.aop;

/**
 * @author zy
 * @date 2020/12/2 11:39
 */
public class MathCalculator {

	public int div(int i,int j){
		System.out.println("执行   MathCalculator.div");
		return i/j;
	}
}
