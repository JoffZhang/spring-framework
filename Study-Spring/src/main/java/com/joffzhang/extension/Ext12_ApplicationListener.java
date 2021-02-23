package com.joffzhang.extension;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author zy
 * @date 2021/1/27 11:13
 *
 * {@link ApplicationListener}
 * 不算一个扩展点，ApplicationListener可以监听某个事件enet，出发时间可穿插在业务方法执行过程中，用户可以自定义某个业务事件。
 * 但是spring内部也有一些内置事件，这种事件，可穿插在启动调用中。
 * 可利用这个特性，来做一些内置事件的监听器来达到和前面一些触发点大致相同的事情
 *
 * spring主要的内置事件：
 * 	ContextRefreshedEvent	ApplicationContext被初始化或刷新时，发布该事件。
 * 	此处初始化指，所有Bean被成功装载，后处理Bean被检测并激活，所有SingletonBean被预实例化，ApplicationContext容器已就绪可用
 * 	ContextStartedEvent	当使用 ConfigurableApplicationContext （ApplicationContext子接口）接口中的 start() 方法启动 ApplicationContext时，该事件被发布。
 * 	你可以调查你的数据库，或者你可以在接受到这个事件后重启任何停止的应用程序。
 * 	ContextStopedEvent	当使用 ConfigurableApplicationContext接口中的 stop()停止ApplicationContext时，发布这个事件。你可以在接受到这个事件后做必要的清理的工作
 * 	ContextClosedEvent	当使用 ConfigurableApplicationContext接口中的 close()方法关闭 ApplicationContext 时，该事件被发布。一个已关闭的上下文到达生命周期末端；它不能被刷新或重启
 * 	RequestHandledEvent	这是一个 web-specific 事件，告诉所有 bean HTTP 请求已经被服务。只能应用于使用DispatcherServlet的Web应用。在使用Spring作为前端的MVC控制器时，当Spring处理用户请求结束后，系统会自动触发该事件
 */
public class Ext12_ApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("[12_ApplicationListener] onApplicationEvent(ApplicationEvent event)");
	}
}
