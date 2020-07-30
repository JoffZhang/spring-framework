package com.joffzhang.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zy
 * @date 2020/7/27 17:01
 */
public class EventTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/joffzhang/spring/event/event.xml");
		MyEvent mySelfEvent = new MyEvent("HELLO", "MSG");
		applicationContext.publishEvent(mySelfEvent);
	}
}
