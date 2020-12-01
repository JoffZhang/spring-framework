package com.joffzhang.annotation;

import com.joffzhang.annotation.bean.Person;
import com.joffzhang.annotation.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解使用
 * @author zy
 * @date 2020/11/30 10:41
 *
 * 扫描包
 * @ComponentScan
 * @ComponentScans
 * 	------------------------------> 扫描规则 过滤规则 @Filter
 * 		@Component
 * 		@Repository
 * 		@Service
 * 		@Controller
 * 		@RestController
 * 		@Configuration
 *
 * 	给容器中注册组件：
 * 	1.包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）
 * 	2.@Bean[导入三方包里的组件]
 * 	3.@Import[快速给容器中导入一个组件]
 * 		1.@Import(要导入的组件)，容器会自动注册这个组件，id默认是类全限定名
 * 		2.ImportSelector:返回需要导入的组件的全类名数组
 * 		3.ImportBeanDefinitionRegistrar:手动注册bean到容器中
 *  4.使用Spring提供的FactoryBean（工厂Bean）
 *  	1.默认获取的是工厂bean调用getObject创建的对象
 *  	2.要获取工厂Bean本身，需要给id前添加	&	&colorFactoryBean
 *
 *  * bean的生命周期
 *  * 		bean创建---初始化---销毁的过程
 *  * 	容器管理bean的生命周期
 *  * 	可自定义初始化和销毁方法：容器在bean进行到当前声明周期的时候来调用自定义的初始化和销毁方法
 *  *
 *  *
 *  * 构造（对象创建）
 *  * 		单实例：容器启动时创建
 *  * 		多实例：每次获取对象时创建
 *  *
 *  *	BeanPostProcessor.postProcessorBeforeInitialization
 *  * 	初始化：	对象创建完成，并赋值好，调用初始化方法
 *  * 	BeanPostProcessor.postProcessorAfterInitialization
 *  *	销毁：	单实例：容器关闭时
 *  *			多实例：容器不会管理这个bean 容器不会自动调用销毁
 *  *
 *  * 遍历得到容器中所有的BeanPostProcessor，挨个执行beforeInitialization.
 *  * 		返回null，跳出循环，不会执行后面的BeanPostProcessor.postProcessBeforeInitialization
 *  *
 *  * BeanPostProcessor原理：
 *  * populateBean(beanName, mbd, instanceWrapper)	给bean进行属性赋值
 *  * initializeBean(beanName, exposedObject, mbd){
 *  * 		applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)
 *  * 		invokeInitMethods(beanName, wrappedBean, mbd)	//执行自定义初始化
 *  * 		applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)
 *  * }
 *  *
 *  *
 *  * 	1.指定初始化和销毁方法
 *  * 		@Bean(initMethod = "init",destroyMethod = "destroy")
 *  *	2.通过让bean实现	InitializingBean（定义初始化逻辑）afterPropertiesSet
 *  *					DisposableBean	(定义销毁逻辑)	destroy
 *  *	3.使用JSR250
 *  * 		@PostConstruct	:bean创建完成并且属性赋值完成，完成初始化逻辑
 *  * 		@PreDestroy		:在容器销毁bean之前通知我们进行销毁
 *  *	4.BeanPostProcessor [interface] : bean 后置处理器
 *  *		在bean初始化前后进行一些处理
 *  *		postProcessorBeforeInitialization:在初始化之前工作
 *  *		postProcessorAfterInitialization：在初始化之后工作
 *  *
 *  *spring底层对BeanPostProcessor的使用：
 *  * 	bean赋值，注入其他组件，@Autowired ，生命周期注解功能，@Async，。。 BeanPostProcessor
 *
 *	属性赋值：
 *
 *  使用@Value赋值；
 * 	1、基本数值
 * 	2、可以写SpEL； #{}
 * 	使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中;加载完外部的配置文件以后使用${}取出配置文件的值
 * 	3、可以写${}；取出配置文件【properties】中的值（在运行环境变量里面的值）
 */
public class MainAnnotation {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Person bean = applicationContext.getBean(Person.class);
		System.out.println(bean);

		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String s : beanNamesForType) {
			System.out.println(s);
		}
	}
}
