package com.joffzhang.extension;

import com.joffzhang.PrintLog;
import org.springframework.beans.factory.BeanNameAware;

/**
 * @author zy
 * @date 2021/1/27 10:00
 * {@link BeanNameAware} 触发点在bean的初始化之前，也就是postProcessBeforeInitialization之前。
 * 使用场景：用户可在初始化bean之前拿到spring容器中注册beanName，来自行修改这个beanName的值
 */
public class Ext07_BeanNameAware implements BeanNameAware {
	@Override
	public void setBeanName(String name) {
		System.out.println("[07_BeanNameAware] setBeanName(String name)");
		PrintLog.printNote("可在初始化bean之前，拿到spring容器中注册的beanName，进行修改");
		PrintLog.printCase("原始beanName = " + name);
		PrintLog.printCase("修改beanName = " + (name = name.replace("_","")));
	}
}
