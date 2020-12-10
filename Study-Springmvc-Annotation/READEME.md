
    1、Web容器在启动的时候，会扫描每个jar包下的   META-INF/services/javax.servlet.ServletContainerInitializer 文件

    2、加载这个文件指定的类 
    spring-web/META-INF/services/javax.servlet.ServletContainerInitializer
           =   org.springframework.web.SpringServletContainerInitializer
    3、springweb应用已启动会加载相关的WebApplicationInitializer接口下的相关所有组件
    4、并且是WebApplicationInitializer组件创建对象(组件不是接口、不是抽象类)
        1.org.springframework.web.context.AbstractContextLoaderInitializer
            AbstractContextLoaderInitializer：创建根容器；
                registerContextLoaderListener(servletContext)
                    -》createRootApplicationContext();
                
        2.org.springframework.web.servlet.support.AbstractDispatcherServletInitializer
            AbstractDispatcherServletInitializer：
                创建Web的ioc容器；createServletApplicationContext();
                创建DispatcherServlet;createDispatcherServlet(servletAppContext);
                将创建的DispatcherServlet添加到ServletContext中；
                注册映射getServletMappings()
                注册Filter
                    getServletFilters();
                        registerServletFilter(servletContext, filter);
                注册自定义
                    customizeRegistration(registration);
        3.org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
            AbstractAnnotationConfigDispatcherServletInitializer：
                创建跟容器：createRootApplicationContext()
                            getRootConfigClasses();获取配置类
                创建web的ioc容器：createServletApplicationContext()
                                    getServletConfigClasses();获取配置类信息
                                    
                                    
####    总结：
    以注解方式来启动SpringMVC；继承AbstractAnnotationConfigDispatcherServletInitializer
    实现抽象方法指定DispatcherServlet的配置信息
    
                                     
![两个容器区分不同功能](./basefile/mvc-context-hierarchy.png)



***
![spring mvc官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-config-view-resolvers)
#   定制SpringMvc
    1.  @EnableWebMvc   :   开启SpringMvc定制配置功能
        <mvc:annotation-driven/>
    2.  配置组件(视图解析器、视图映射、静态资源映射、拦截器。。。)
        WebMvcConfigurer子类仅覆盖其感兴趣的方法
            addInterceptors
            addCorsMappings