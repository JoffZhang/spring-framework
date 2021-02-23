package com.joffzhang.extension;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author zy
 * @date 2021/1/25 17:34
 *
 * {@link ApplicationContextInitializer} 整个spring容器在刷新之前初始化ConfigurableApplicationContext的回调接口，
 * 即容器刷新之前调用此类的initialize方法。
 * 可以想到的场景可能为，在最开始激活一些配置，或者利用这时候class还没被类加载器加载的时机，进行动态字节码注入等操作。
 * 		典型的应用场景是web应用中需要编程方式对应用上下文做初始化。比如，注册属性源(property sources)或者针对上下文的环境信息environment激活相应的profile。
 * spring中还未找到调用的地方，但在springmvc springboot中都有相关调用
 */
// 支持Order  value值越小越先执行   注：在类上标注，不是方法上
//@Order(2)
public class Ext01_ApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println("[01_ApplicationContextInitializer] initialize(ConfigurableApplicationContext applicationContext)");
	}
}
