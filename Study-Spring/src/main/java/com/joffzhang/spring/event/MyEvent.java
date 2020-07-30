package com.joffzhang.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zy
 * @date 2020/7/27 17:23
 * 1.定义监听事件
 */
@SuppressWarnings("MYLISTENER")
public class MyEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	/**
	 * Create a new {@code ApplicationEvent}.
	 *
	 * @param source the object on which the event initially occurred or with
	 *               which the event is associated (never {@code null})
	 */
	public MyEvent(Object source) {
		super(source);
	}

	private String msg;
	public MyEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}

	public void print(){
		System.out.println("joff Event "+msg);
	}
}
