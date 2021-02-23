package com.joffzhang.extension;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/1/27 11:09
 *
 * {@link DisposableBean}
 * 触发时机：当次对象销毁时
 * 如applicationContext.registerShutdownHook时，就会触发
 */
public class Ext11_DisposableBean implements DisposableBean {
	@Override
	public void destroy() throws Exception {
		System.out.println("[11_DisposableBean] destroy()");
	}
}
