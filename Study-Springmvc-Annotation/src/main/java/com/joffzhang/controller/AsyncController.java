package com.joffzhang.controller;

import com.joffzhang.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author zy
 * @date 2020/12/10 16:50
 */
@Controller
public class AsyncController {


	/**
	 *
	 *
	 * 一旦 在Servlet容器中启用了异步请求处理功能，控制器方法就可以使用来包装任何受支持的控制器方法返回值DeferredResult
	 * 控制器可以从另一个线程异步生成返回值，例如，响应外部事件（JMS消息），计划任务或其他事件
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/createOrder")
	public DeferredResult<Object> createOrder(){
		DeferredResult<Object> deferredResult = new DeferredResult<>(3000L,"create order timeout");

		DeferredResultQueue.save(deferredResult);
		return deferredResult;
	}

	@ResponseBody
	@RequestMapping("create")
	public String create(){
		//创建订单
		String orderNum = UUID.randomUUID().toString();
		DeferredResultQueue.get().setResult(orderNum);
		return orderNum;
	}
	/**
	 * Callable处理流程
	 *
	 *	1.控制器返回Callable。
	 *	2.Spring MVC调用request.startAsync()并将其提交Callable到TaskExecutor一个单独的线程中进行处理。
	 *	3.同时，DispatcherServlet和所有过滤器退出Servlet容器线程，但是响应保持打开状态。
	 *	4.最终Callable产生一个结果，Spring MVC将请求分派回Servlet容器以完成处理。
	 *	5.将DispatcherServlet被再次调用，并且处理从所述异步生产返回值恢复Callable。
	 *
	 * 异步拦截器：
	 * 	1.原生API的AsyncListener
	 * 	2.SpringMVC：实现AsyncHandlerInterceptor
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/async01")
	public Callable<String> async01(){
		System.out.println(Thread.currentThread()+" "+System.currentTimeMillis()+" AsyncController async01 start");
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println(Thread.currentThread()+" "+System.currentTimeMillis() + " AsyncController async01 Callable call start");
				TimeUnit.SECONDS.sleep(2);
				System.out.println(Thread.currentThread()+" "+System.currentTimeMillis() + " AsyncController async01 Callable call end");
				return "AsyncController..async01..Callable...";
			}
		};
		System.out.println(Thread.currentThread()+" "+System.currentTimeMillis()+" AsyncController async01 end");
		return callable;
	}
}
