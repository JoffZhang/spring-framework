package com.joffzhang.extension;

import com.joffzhang.PrintLog;
import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * @author zy
 * @date 2021/1/27 11:02
 *
 * {@link SmartInitializingSingleton} afterSingletonsInstantiated 作用是在spring容器管理的所有单例对象（非懒加载）初始化完成之后调用回调接口。
 * 触发时机：postProcessAfterInitialization之后
 * 使用场景： 可在对所有单例对象初始化完毕后，做一些后置业务处理
 */
public class Ext10_SmartInitializingSingleton implements SmartInitializingSingleton {
	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("[10_SmartInitializingSingleton] afterSingletonsInstantiated()");
		PrintLog.printNote("可在所有单例对象初始化完毕后，做一些后置业务处理");
	}
}
