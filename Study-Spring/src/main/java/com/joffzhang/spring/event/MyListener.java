package com.joffzhang.spring.event;


import org.springframework.context.ApplicationListener;

/**
 * @author zy
 * @date 2020/7/27 16:53
 *2.定义监听器
 */

public class MyListener implements ApplicationListener<MyEvent> {
//因为使用了泛型，方法如下。
//	@Override
//	public void onApplicationEvent(ApplicationEvent event) {
//		if(event instanceof TestEvent){
//			TestEvent testEvent = (TestEvent) event;
//			testEvent.print();
//		}
//	}

	@Override
	public void onApplicationEvent(MyEvent event) {
		event.print();
	}
}
