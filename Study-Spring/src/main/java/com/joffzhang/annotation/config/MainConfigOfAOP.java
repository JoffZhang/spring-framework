package com.joffzhang.annotation.config;

import com.joffzhang.annotation.aop.LogAspects;
import com.joffzhang.annotation.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP:	动态代理
 * 		指在程序运行期间动态的将某段代码切入到指定的方法指定位置进行运行的编程方式
 *
 * 1、导入aop模块；Spring AOP：(spring-aspects)
 * 2、定义一个业务逻辑类（MathCalculator）；在业务逻辑运行的时候将日志进行打印（方法之前、方法运行结束、方法出现异常，xxx）
 * 3、定义一个日志切面类（LogAspects）：切面类里面的方法需要动态感知MathCalculator.div运行到哪里然后执行；
 *
 * 	通知方法：
 * 			前置通知(@Before)：logStart：在目标方法(div)运行之前运行
 * 			后置通知(@After)：logEnd：在目标方法(div)运行结束之后运行（无论方法正常结束还是异常结束）
 * 			返回通知(@AfterReturning)：logReturn：在目标方法(div)正常返回之后运行
 * 			异常通知(@AfterThrowing)：logException：在目标方法(div)出现异常以后运行
 * 			环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.procced()）
 * 4、给切面类的目标方法标注何时何地运行（通知注解）；
 * 5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中;
 * 6、必须告诉Spring哪个类是切面类(给切面类上加一个注解：@Aspect)
 * [7]、给配置类中加 @EnableAspectJAutoProxy 【开启基于注解的aop模式】
 * 		在Spring中很多的 @EnableXXX;
 *
 * 三步：
 * 	1）、将业务逻辑组件和切面类都加入到容器中；告诉Spring哪个是切面类（@Aspect）
 * 	2）、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 *  3）、开启基于注解的aop模式；@EnableAspectJAutoProxy
 *
 *	AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
 *         @EnableAspectJAutoProxy
 *	1.@EnableAspectJAutoProxy:
 *         @EnableAspectJAutoProxy
 *       		@Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
 *       			利用AspectJAutoProxyRegistrar自定义给容器中注册bean；BeanDefinition
 *       			internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
 *				给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
 *
 * 2.AnnotationAwareAspectJAutoProxyCreator
 *		->AspectJAwareAdvisorAutoProxyCreator
 *			->AbstractAdvisorAutoProxyCreator
 *				->AbstractAutoProxyCreator   implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *				关注后置处理器（在bean初始化完成前做事情），自动装配BeanFactory
 *
 *	AbstractAutoProxyCreator.setBeanFactory()
 *	AbstractAutoProxyCreator.后置处理器
 *	↑
 *  AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory beanFactory) -》 initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *  ↑
 *  	//AspectJAwareAdvisorAutoProxyCreator
 *	↑
 *	AnnotationAwareAspectJAutoProxyCreator.initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *
 * 流程：
 * 	1.传入配置类，创建IOC容器
 * 	2.注册配置类，调用refresh()刷新容器
 * 	3.registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
 * 		1.先获取ioc容器已定义的需要创建对象的所有BeanPostProcessor
 * 		2.给容器添加别的BeanPostProcessor   BeanPostProcessorChecker
 * 		3.将实现PriorityOrdered、Ordered的实现类BeanPostProcessor与其他区分开
 * 			优先注册实现PriorityOrdered的BeanPostProcessors
 * 			其次注册实现Ordered的BeanPostProcessors
 *			注册常规BeanPostProcessors
 * 		4.重新注册所有内部MergedBeanDefinitionPostProcessor
 * 		5.重新注册后处理器以将内部bean检测为ApplicationListener，将其移至处理器链的末尾（用于拾取代理等）ApplicationListenerDetector implements DestructionAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor
 *			创建org.springframework.aop.config.internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
 *			1.创建bean实例
 *			2.populateBean(beanName, mbd, instanceWrapper); 填充bean，属性值注入
 *			3.initializeBean(beanName, exposedObject, mbd); 调用自定义初始化方法
 *				1.invokeAwareMethods(beanName, bean)；处理Aware接口的方法回调
 *				2.applyBeanPostProcessorsBeforeInitialization();执行后置处理器的postProcessBeforeInitialization
 *				3.invokeInitMethods(beanName, wrappedBean, mbd);执行自定义的init方法
 *				4.applyBeanPostProcessorsBeforeInitialization();执行后置处理器的postProcessAfterInitialization
 *			4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功  ->aspectJAdvisorsBuilder
 *		 6.把BeanPostProcessor注册到BeanFactory中；
 *  			beanFactory.addBeanPostProcessor(postProcessor);
 * =======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程========
 *		AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor
 *														postProcessBeforeInstantiation
 *														postProcessAfterInstantiation
 *	4.finishBeanFactoryInitialization(beanFactory);完成beanFactory初始化，完成剩下的单实例
 *		1.遍历获取容器中的所有Bean，依次创建对象getBean(beanName)
 *			getBean->doGetBean()->getSingleton()->
 *		2.创建bean
 *			【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，会调用postProcessBeforeInstantiation()】
 *			getSingleton(beanName)
 *			1.先从缓存中获取当前bean，如果能获取，说明bean之前被创建过，直接使用，否则再创建
 *				只要创建好的bean都会被缓存起来
 *
 *			2.createBean()创建bean
 *				AnnotationAwareAspectJAutoProxyCreator 会在任何bean创建之前先尝试返回bean的实例
 *				【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
 *				【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
 *				1.resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
 *					希望后置处理器在此能返回一个代理对象；如果能返回代理对象就使用，不能就继续
 *					bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 					if (bean != null) {
 * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                  }
 *              2.doCreateBean(beanName, mbdToUse, args);真正的去创建一个bean实例；和3.5流程一样；
 *
 *AnnotationAwareAspectJAutoProxyCreator 【InstantiationAwareBeanPostProcessor】的作用：
 *	1.每个bean创建之前，调用postProcessBeforeInstantiation（）
 * 		关注MathCalculator和LogAspect的创建
 * 		1.判断当前bean是否在	advisedBeans 中（保存了所有需要增强的bean）
 * 		2.判断当前bean是否是基础类型的	Advice,Pointcut,Advisor,AopInfrastructureBean
 * 			或者是否是切面（@Aspect)
 *		3.是否需要跳过	shouldSkip()
 *			1，获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors = findCandidateAdvisors();】
 *				每一个封装的通知方法的增强器是	InstantiationModelAwarePointcutAdvisor
 *				判断每一个增强器是否是	AspectJPointcutAdvisor类型的;	返回true
 *			2.返回false
 *	2.创建对象
 *		postProcessAfterInitialization
 *			return wrapIfNecessary(bean, beanName, cacheKey);
 *			wrapIfNecessary：如果bean适合被代理，则需要封装指定bean
 *		1.获取当前bean的所有增强器（通知方法） Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
 *			1.找到候选的所有增强器（找哪些通知方法是需要切入当前bean的方法的）
 *			2.获取到能在bean使用的增强器
 *			3.增强器排序
 *		2.保存当前bean在advisedBeans中
 *		3.如果当前bean需要增强，创建当前bean的代理对象
 *		Object proxy = createProxy(
 * 					bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
 * 			1.获取所有增强器（通知方法）
 * 			2.保存到proxyFactory中
 * 			3.创建代理对象：spring自动决定
 * 				JdkDynamicAopProxy		jdk动态代理
 * 				ObjenesisCglibAopProxy	cglib的动态代理
 *			4.给容器中返回当前组件使用cglib增强了的代理对象
 *			5.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
 *
 *	3.目标方法执行
 *		容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，xx)
 *		1.CglibAopProxy.intercept()	拦截目标方法的执行
 *		2.根据ProxyFactory对象获取将要执行的目标方法连接器链
 *		List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *			List<Object> interceptorList保存所有的拦截器  5(1个默认的ExposeInvocationInterceptor 和4个增强器)
 *			遍历所有的增强器，将其转为Interceptor
 *			MethodInterceptor[] interceptors = registry.getInterceptors(advisor);
 *			  将增强器转为List<MethodInterceptor>
 *				如果是MethodInterceptor,直接添加到集合中
 *				如果不是则使用AdvisorAdapter将增强器转为MethodInterceptor
 *				转换完成返回MethodInterceptor[]
 *		3.如果没有拦截器链	，直接执行目标方法。
 *			拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制
 *		4.如果有拦截器链，把需要执行的目标对象，目标方法，
 *			拦截器等信息传入创建一个CglibMethodInvocation对象,
 *			并执行mi.proceed()
 *			Object retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 *		5.拦截器触发的过程
 *			1.如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法
 *			2.链式获取每一个拦截器，拦截器执行invoke()，每一个拦截器等待下一个拦截器执行完成返回以后再来执行。
 *				拦截器链的机制，保证通知方法与目标方法的执行顺序
 *	CglibMethodInvocation.procced();
 *		currentInterceptorIndex:记录当前拦截器执行的索引，从-1，每次执行procced,索引自增一次
 *	procced(){invoke(MethodInvocation mi)}
 *									invoke(this)	 this->ReflectiveMethodInvocation
 *								////////
 *	ExposeInvocationInterceptor															---------------------
 * 		|								|																	↑
 * 	  mi.proceed()						|													finally {invocation.set(oldInvocation);}
 *		↓						////////																    |
 * 	AspectJAfterThrowingAdvice															---------------------
 * 		|								|																	↑
 * 	  mi.proceed()						|							【catch (Throwable ex) {invokeAdviceMethod(getJoinPointMatch(), null, ex)}异常通知】
 *		↓						////////																	|
 * 	AfterReturningAdviceInterceptor														---------------------
 * 		|								|																	↑
 * 	  mi.proceed()						|															【this.advice.afterReturning返回通知】
 *		↓ 						////////																	|
 * 	AsepctJAfterAdvice																	---------------------
 * 		|								|														            ↑
 * 	  mi.proceed()						|							【finally {invokeAdviceMethod(getJoinPointMatch(), null, null);}后置通知】
 *		↓ 						////////																	|
 * 	AspectJAroundAdvice					|												---------------------
 * 		|								|														            ↑
 * 	invokeAdviceMethod					|																	|
 * 	->invokeAdviceMethodWithGivenArgs	|														            |
 * 	->this.aspectJAdviceMethod.invoke	|														            |
 *		↓ 						////////																	|
 * 	MethodBeforeAdviceInterceptor    																		|
 * 		--> 【this.advice.before前置通知】 --> mi.proceed() invokeJoinpoint()		--> method.invoke(target, args)
 *
 *
 *
 * 总结：
 *	1.@EnableAspectJAutoProxy 开启AOP功能
 *	2.@EnabelAspectJAutoProxy会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
 *	3.AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 *	4.容器的创建流程：
 *		1.registerBeanPostProcessors()注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象
 *		2.finishBeanFactoryInitialization()初始化剩下的单实例bean
 *			1.创建业务逻辑组件和切面
 *			2.AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 *			3.组件创建完毕后，判断组件是否需要增强
 *				是：切面的通知方法，包装秤增强器（Advisor）；给业务逻辑组件创建一个代理对象（cglib）
 *	5.执行目标方法：
 *		1.代理对象执行目标方法
 *		2.CglibAopProxy.intercept()
 *			1.得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
 *			2.利用拦截器的链式调用机制，一次进人每个拦截器进行执行
 *			3.效果
 *		版本不同执行顺序存在差异  spring5
 *				正常执行： 环绕通知=》前置通知=》目标方法=》后置通知=》返回通知
 *				异常执行： 环绕通知=》前置通知=》目标方法=》后置通知=》异常通知
 *
 *
 * @author zy
 * @date 2020/12/2 11:29
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

	//业务逻辑类加入容器中
	@Bean
	public MathCalculator mathCalculator(){
		return new MathCalculator();
	}

	//切面类加入到容器中
	@Bean
	public LogAspects logAspects(){
		return new LogAspects();
	}
}
