package com.joffzhang.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author zy
 * @date 2020/12/10 17:21
 */
public class DeferredResultQueue {
	private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedDeque<>();
	public static void save(DeferredResult<Object> deferredResult){
		queue.add(deferredResult);
	}

	public static DeferredResult<Object> get(){
		return queue.poll();
	}
}
