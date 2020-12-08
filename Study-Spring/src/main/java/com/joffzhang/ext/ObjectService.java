package com.joffzhang.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2020/12/7 11:36
 */
@Service
public class ObjectService {

	@EventListener(classes={ApplicationEvent.class})
	public void listener(ApplicationEvent event){
		System.out.println("ObjectService listener() @EventListener 获取事件："+event);
	}
}
