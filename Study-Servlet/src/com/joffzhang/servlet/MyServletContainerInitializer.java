package com.joffzhang.servlet;

import com.joffzhang.service.HelloService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * Servlet容器初始化器
 * @author zy
 * @date 2020/12/9 14:35
 */
//容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类、子接口等）传递过来
	//传入感兴趣的相关类型
@HandlesTypes(value = {
		HelloService.class
})
public class MyServletContainerInitializer implements ServletContainerInitializer {
	/**
	 * 应用启动的时候，会运行onStartup方法
	 *
	 * @param set	Set<Class<?>>:	感兴趣类型的所有子类型
	 * @param servletContext	ServletContext：代表当前Web应用的ServletContext；一个Web应用一个ServletContext
	 * @throws ServletException
	 *	注册组件：
	 * 	自己实现可以使用注解@WebServlet、@WebFilter、@WebListener等				@WebInitParam
	 * 	当引入三方包时,可用ServletContext注册
	 *
	 *	使用ServletContext注册Web组件（Servlet、Filter、Listener）
	 *	使用编码的方式，在项目启动的时候给ServletContext里面添加组件
	 *		必须在项目启动时添加；
	 *		1、{@link ServletContainerInitializer#onStartup(Set, ServletContext)} 获取ServletContext
	 *		2、{@link ServletContextListener#contextInitialized(ServletContextEvent)} ServletContextEvent获取ServletContext
	 */
	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
		System.out.println("相关类型：");
		set.forEach(aClass -> {
			System.out.println(aClass);
		});
		//代码注册组件
		ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
		//配置servlet的映射信息
		userServlet.addMapping("/user");

		servletContext.addListener(UserListener.class);

		FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
		//配置filter的映射信息
		userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");

	}
}
