package com.joffzhang.extension;

import com.joffzhang.PrintLog;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/1/27 10:38
 *
 * {@link InitializingBean} 用来初始化bean的，为bean提供了初始化方法的方式，在bean初始化时候都会执行该afterPropertiesSet方法。
 * 触发时机：在postProcessAfterInitialization之前
 * 使用场景：系统启动时进行一些业务指标的初始化工作
 */

public class Ext08_InitializingBean implements InitializingBean {
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("[08_InitializingBean]  afterPropertiesSet()");
		PrintLog.printNote("系统启动时一些业务指标的初始化工作");
	}
}
