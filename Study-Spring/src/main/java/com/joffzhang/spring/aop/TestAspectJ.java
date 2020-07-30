package com.joffzhang.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author zy
 * @date 2020/7/28 11:29
 */
@Aspect
public class TestAspectJ {

	@Pointcut("execution(* com.joffzhang.spring.aop.*.test(..))")
	public void test(){}


	@Before("test()")
	public void testBefore(){
		System.out.println("TestAspectJ.testBefore");
	}

	@After("test()")
	public void testAfter(){
		System.out.println("TestAspectJ.testAfter");
	}
	@Around("test()")
	public Object testAround(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("TestAspectJ.testAround  before");
		Object o = null;
		try {
			o = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		System.out.println("TestAspectJ.testAround  after");
		return o;
	}

}
