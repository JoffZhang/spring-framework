package com.joffzhang.extension;

//import org.springframework.context.support.ApplicationContextAwareProcessor;

/**
 * @author zy
 * @date 2021/1/27 9:37
 *	{@link org.springframework.context.support.ApplicationContextAwareProcessor} 该类本身没有扩展点，但是类内部却有6个扩展点可供实现，
 *	这些类触发的时机在bean实例化之后，初始化之前
 *	org.springframework.context.support.ApplicationContextAwareProcessor#invokeAwareInterfaces(java.lang.Object){
 *      //用于获取EnvironmentAware的一个扩展类，这个变量非常有用，可以获得系统内的所有参数。一般不需扩展，因spring内部都可以通过注入的方式直接获得
 *	    if (bean instanceof EnvironmentAware) {
 * 			((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
 *                }
 * 		//EmbeddedValueResolverAware用于获取StringValueResolver的一个扩展类，StringValueResolver用于获取基于String类型的properties的变量，一般我们都用@Value方式获取，
 * 		//如果实现了该接口，把StringValueResolver缓存起来，通过这个类去获取String类型的变量，效果一样
 * 		if (bean instanceof EmbeddedValueResolverAware) {
 * 			((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
 *        }
 * 		//获取ResourceLoaderAware的一个扩展类，ResourceLoader可以用于获取classpath内所有的资源对象，可以扩展此类拿到ResourceLoader对象
 * 		if (bean instanceof ResourceLoaderAware) {
 * 			((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
 *        }
 *      //ApplicationEventPublisherAware用于获取ApplicationEventPublisher的扩展类，ApplicationEventPublisher可以用来发布事件，结合ApplicationListener使用。
 * 		if (bean instanceof ApplicationEventPublisherAware) {
 * 			((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
 *        }
 *      //MessageSourceAware用来获取MessageSource的扩展类，主要用来做国际化
 * 		if (bean instanceof MessageSourceAware) {
 * 			((MessageSourceAware) bean).setMessageSource(this.applicationContext);
 *        }
 *      //ApplicationContext用来获取ApplicationContext的一个扩展类，可以手动方式获取spring上下文注册的bean，我们经常可以扩展该接口缓存spring上下文，包装成静态方法。
 *      //同时ApplicationContext也实现了BeanFactory，MessageSource，ApplicationEventPublisher等接口，也可做相关接口的事情
 * 		if (bean instanceof ApplicationContextAware) {
 * 			((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
 *        }
 *	}
 *	该类用于执行各种驱动接口，在bean实例化之后，属性填充之后，通过执行以上方法中的扩展接口，来获取对应容器的变量。
 *
 *	ApplicationContextAwareProcessor 该类不是public的
 */
//public class Ext06_ApplicationContextAwareProcessor implements ApplicationContextAwareProcessor {
//}
