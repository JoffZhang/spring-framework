package com.joffzhang.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

/**
 * 切面
 * 	@Aspect	告诉Spring当前类是一个切面类
 * @author zy
 * @date 2020/12/2 11:40
 */
@Aspect
public class LogAspects {

	//抽取公共的切入点表达式
	//1.本类引用
	//2.其他的切面引用
	@Pointcut("execution(public int com.joffzhang.annotation.aop.MathCalculator.*(..))")
	public void pointCut(){
	}

	//@Before在目标方法之前切入;切入点表达式（指定在哪个方法切入）
	@Before("pointCut()")
	public void logStart(JoinPoint joinPoint){
		System.out.println(joinPoint.getSignature().getName()+"  @Before方法运行logStart， 方法参数 {"+ Arrays.asList(joinPoint.getArgs())+"}");
	}
	@After("com.joffzhang.annotation.aop.LogAspects.pointCut()")
	public void logEnd(JoinPoint joinPoint){
		System.out.println(joinPoint.getSignature().getName()+"  @After方法运行logEnd");
	}
	//JoinPoint一定要出现在参数表的第一位
	@AfterReturning(value = "pointCut()",returning = "result")
	public void logReturn(JoinPoint joinPoint , Object result){
		System.out.println(joinPoint.getSignature().getName()+"  @AfterReturning方法运行logReturn ，返回结果 {"+result+"}");
	}
	@AfterThrowing(value = "pointCut()",throwing = "e")
	public void logException(JoinPoint joinPoint,Exception e){
		System.out.println(joinPoint.getSignature().getName()+"  @AfterThrowing方法运行logException ，异常信息 {"+e+"}");
	}

	@Around(value = "pointCut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println(joinPoint.getSignature().getName() + "  @Around方法运行logAround ");
		//获取方法参数数组
		Object[] args = joinPoint.getArgs();
		System.out.println(joinPoint.getSignature().getName() + "  @Around方法运行logAround 参数："+Arrays.asList(args));
		//得到方法签名
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//获取方法参数类型数组
		Class<?>[] parameterTypes = signature.getParameterTypes();
		System.out.println(joinPoint.getSignature().getName() + "  @Around方法运行logAround 参数类型: "+ Arrays.asList(parameterTypes));
		//注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
		Object result = joinPoint.proceed(args);
		System.out.println(joinPoint.getSignature().getName() + "  @Around方法运行logAround 返回结果："+result);
		return result;
	}
}
