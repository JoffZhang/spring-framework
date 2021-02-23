package com.joffzhang.lifecycle.context;

import com.joffzhang.PrintLog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 * @date 2021/2/1 11:14
 */
@Configuration
@ComponentScan("com.joffzhang.lifecycle.context")
public class ContextLifeCycle {

	/**
	 * [初始化Spring容器] new AnnotationConfigApplicationContext(SpringLifecycle.class)
	 *     |----------------------------------------> AutoBean  执行start方法
	 * [启动Spring容器] applicationContext.start()
	 *     |----------------------------------------> NoAutoBean  执行start方法
	 * [停止Spring容器] applicationContext.stop()
	 *     |----------------------------------------> AutoBean  执行stop方法
	 *     |----------------------------------------> NoAutoBean  执行stop方法
	 *
	 * 当SmartLifecycle的isAutoStartup为true时，只要Spring容器刷新了就会自动执行start方法，而isAutoStartup为false时，只有当容器执行了start方法之后才会执行start方法。
	 * 所以如果想要Lifecycle对象随着Spring容器初始化之后就开启，就可以采用实现SmartLifecycle接口的方式。
	 *
	 * 总结
	 * 1、Lifecycle接口用于实现bean的生命周期，执行start方法开启生命周期；执行stop方法结束生命周期
	 * 2、ApplicationContext通过LifecycleProcessor来管理所有bean的生命周期，当Spring容器启动或者刷新时，都会调用容器中所有实现了Lifecycle接口的bean的start方法，关闭时调用所有实现了Lifecycle接口的bean的stop方法
	 * 3、SmartLifecycle可以实现异步回调执行stop方法，用于结束生命周期之前的收尾业务逻辑处理；并且可以设置是否在容器刷新时自动开启生命周期
	 */
	public static void main(String[] args) {
		PrintLog.printMark("初始化Spring容器","new AnnotationConfigApplicationContext(SpringLifecycle.class)");
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ContextLifeCycle.class);
		PrintLog.printMark("启动Spring容器","applicationContext.start()");
		PrintLog.printNote("isAutoStartup true随容器刷新而启动，之后不再重复执行启动");
		applicationContext.start();
		PrintLog.printMark("停止Spring容器","applicationContext.stop()");
		applicationContext.stop();
		PrintLog.printMark("关闭Spring容器","applicationContext.close()");
		PrintLog.printNote("close也调用的是stopBeans，因之前stop已执行过，所以直接掠过，不再执行");
		applicationContext.close();
	}
}
