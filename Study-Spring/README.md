##扫描包
 *      @ComponentScan
 *      @ComponentScans
            扫描规则 过滤规则 @Filter
    	@Component
    	@Repository
    	@Service
    	@Controller
    	@RestController
    	@Configuration
##	给容器中注册组件：
    1.包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）
    2.@Bean[导入三方包里的组件]
    3.@Import[快速给容器中导入一个组件]
 *      1.@Import(要导入的组件)，容器会自动注册这个组件，id默认是类全限定名
 *      2.ImportSelector:返回需要导入的组件的全类名数组
 *      3.ImportBeanDefinitionRegistrar:手动注册bean到容器中
 *  4.使用Spring提供的FactoryBean（工厂Bean）
 *      1.默认获取的是工厂bean调用getObject创建的对象
 *      2.要获取工厂Bean本身，需要给id前添加	&	&colorFactoryBean
##bean的生命周期
        bean创建---初始化---销毁的过程
        容器管理bean的生命周期
        可自定义初始化和销毁方法：容器在bean进行到当前声明周期的时候来调用自定义的初始化和销毁方法

 *  构造（对象创建）
 
        单实例：容器启动时创建
        多实例：每次获取对象时创建

        BeanPostProcessor.postProcessorBeforeInitialization
        初始化：	对象创建完成，并赋值好，调用初始化方法
        BeanPostProcessor.postProcessorAfterInitialization
        销毁：	单实例：容器关闭时
                多实例：容器不会管理这个bean 容器不会自动调用销毁

        遍历得到容器中所有的BeanPostProcessor，挨个执行beforeInitialization.
                返回null，跳出循环，不会执行后面的BeanPostProcessor.postProcessBeforeInitialization
        
###    BeanPostProcessor原理：
```
    populateBean(beanName, mbd, instanceWrapper)	给bean进行属性赋值
    initializeBean(beanName, exposedObject, mbd){
     		applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName)
     		invokeInitMethods(beanName, wrappedBean, mbd)	//执行自定义初始化
     		applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName)
    }
    
    
     	1.指定初始化和销毁方法
     		@Bean(initMethod = "init",destroyMethod = "destroy")
    	2.通过让bean实现	InitializingBean（定义初始化逻辑）afterPropertiesSet
    					DisposableBean	(定义销毁逻辑)	destroy
    	3.使用JSR250
     		@PostConstruct	:bean创建完成并且属性赋值完成，完成初始化逻辑
     		@PreDestroy		:在容器销毁bean之前通知我们进行销毁
    	4.BeanPostProcessor [interface] : bean 后置处理器
    		在bean初始化前后进行一些处理
    		postProcessorBeforeInitialization:在初始化之前工作
    		postProcessorAfterInitialization：在初始化之后工作
    
    spring底层对BeanPostProcessor的使用：
     	bean赋值，注入其他组件，@Autowired ，生命周期注解功能，@Async，。。 BeanPostProcessor
```

### 属性赋值：

-使用@Value赋值；
```
    1、基本数值
    2、可以写SpEL； #{}
        使用@PropertySource读取外部配置文件中的k/v保存到运行的环境变量中;加载完外部的配置文件以后使用${}取出配置文件的值
    3、可以写${}；取出配置文件【properties】中的值（在运行环境变量里面的值）
```

## 自动装配：
    Spring利用依赖注入（DI），完成对IOC容器中各个组件的依赖关系赋值
    
    1.@Autowired:自动注入
        1.默认优先按照类型去容器中找对应的组件：applicationContext.getBean(TestDao.class)找到就赋值
        2.如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找 applicationContext.getBean("testDao")
        3.@Qualifier("testDao"):使用@Qualifier指定需要装配的组件的id，而不是使用属性名
        4.自动装配默认一定要将属性赋值好，没有会报错  可以使用@Autowired(required =false)
        5.@Primary:让spring进行自动装配的时候，默认使用首选的bean；也可以继续使用@Qualifier指定要装配的bean的名字
        BookService{
            @Autowired
            BookDao  bookDao;
        }
    2。Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
        @Resource:
                可以和@Autowired一样实现自动装配功能；默认是按照组件名称进行装配的；
                没有能支持@Primary功能没有支持@Autowired（reqiured=false）;
        @Inject:
                需要导入javax.inject的包，和Autowired的功能一样。没有required=false的功能；
    @Autowired:Spring定义的； @Resource、@Inject都是java规范

      AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能；
    
    	3.@Autowired:构造器，参数，方法，属性；都是从容器中获取参数组件的值
    			1）、[标注在方法位置]：@Bean+方法参数；参数从容器中获取;默认不写@Autowired效果是一样的；都能自动装配
    		    2）、[标在构造器上]：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以自动从容器中获取
    		    3）、放在参数位置：
    	4.自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
    		自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
    		把Spring底层一些组件注入到自定义的Bean中；
    		xxxAware：功能使用xxxProcessor；
    				ApplicationContextAware==》ApplicationContextAwareProcessor；

##  @Profile：
     Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能；
    
     开发环境、测试环境、生产环境；
     数据源：(/A)(/B)(/C)；
    
    
     @Profile：指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
    
     1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
     2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
     3）、没有标注环境标识的bean在，任何环境下都是加载的；

##  AOP:	动态代理
    指在程序运行期间动态的将某段代码切入到指定的方法指定位置进行运行的编程方式
    
     1、导入aop模块；Spring AOP：(spring-aspects)
     2、定义一个业务逻辑类（MathCalculator）；在业务逻辑运行的时候将日志进行打印（方法之前、方法运行结束、方法出现异常，xxx）
     3、定义一个日志切面类（LogAspects）：切面类里面的方法需要动态感知MathCalculator.div运行到哪里然后执行；
    
     	通知方法：
     			前置通知(@Before)：logStart：在目标方法(div)运行之前运行
     			后置通知(@After)：logEnd：在目标方法(div)运行结束之后运行（无论方法正常结束还是异常结束）
     			返回通知(@AfterReturning)：logReturn：在目标方法(div)正常返回之后运行
     			异常通知(@AfterThrowing)：logException：在目标方法(div)出现异常以后运行
     			环绕通知(@Around)：动态代理，手动推进目标方法运行（joinPoint.procced()）
     4、给切面类的目标方法标注何时何地运行（通知注解）；
     5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中;
     6、必须告诉Spring哪个类是切面类(给切面类上加一个注解：@Aspect)
     [7]、给配置类中加 @EnableAspectJAutoProxy 【开启基于注解的aop模式】
     		在Spring中很多的 @EnableXXX;
    
     三步：
        1）、将业务逻辑组件和切面类都加入到容器中；告诉Spring哪个是切面类（@Aspect）
        2）、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
        3）、开启基于注解的aop模式；@EnableAspectJAutoProxy
    
###    	AOP原理：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
             @EnableAspectJAutoProxy
    1.@EnableAspectJAutoProxy:
             @EnableAspectJAutoProxy
           		@Import(AspectJAutoProxyRegistrar.class)：给容器中导入AspectJAutoProxyRegistrar
           			利用AspectJAutoProxyRegistrar自定义给容器中注册bean；BeanDefinition
           			internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
    				给容器中注册一个AnnotationAwareAspectJAutoProxyCreator
    
     2.AnnotationAwareAspectJAutoProxyCreator
    		->AspectJAwareAdvisorAutoProxyCreator
    			->AbstractAdvisorAutoProxyCreator
    				->AbstractAutoProxyCreator   implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
    				关注后置处理器（在bean初始化完成前做事情），自动装配BeanFactory
    
    	AbstractAutoProxyCreator.setBeanFactory()
    	AbstractAutoProxyCreator.后置处理器
    	    ↑
    	AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory beanFactory) -》 initBeanFactory(ConfigurableListableBeanFactory beanFactory)
    	    ↑
    	//AspectJAwareAdvisorAutoProxyCreator
    	    ↑
    	AnnotationAwareAspectJAutoProxyCreator.initBeanFactory(ConfigurableListableBeanFactory beanFactory)
    
    
####     流程：
     	1.传入配置类，创建IOC容器
     	2.注册配置类，调用refresh()刷新容器
     	3.registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
     		1.先获取ioc容器已定义的需要创建对象的所有BeanPostProcessor
     		2.给容器添加别的BeanPostProcessor   BeanPostProcessorChecker
     		3.将实现PriorityOrdered、Ordered的实现类BeanPostProcessor与其他区分开
     			优先注册实现PriorityOrdered的BeanPostProcessors
     			其次注册实现Ordered的BeanPostProcessors
    			注册常规BeanPostProcessors
     		4.重新注册所有内部MergedBeanDefinitionPostProcessor
     		5.重新注册后处理器以将内部bean检测为ApplicationListener，将其移至处理器链的末尾（用于拾取代理等）ApplicationListenerDetector implements DestructionAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor
    			创建org.springframework.aop.config.internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
    			1.创建bean实例
    			2.populateBean(beanName, mbd, instanceWrapper); 填充bean，属性值注入
    			3.initializeBean(beanName, exposedObject, mbd); 调用自定义初始化方法
    				1.invokeAwareMethods(beanName, bean)；处理Aware接口的方法回调
    				2.applyBeanPostProcessorsBeforeInitialization();执行后置处理器的postProcessBeforeInitialization
    				3.invokeInitMethods(beanName, wrappedBean, mbd);执行自定义的init方法
    				4.applyBeanPostProcessorsBeforeInitialization();执行后置处理器的postProcessAfterInitialization
    			4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功  ->aspectJAdvisorsBuilder
    		 6.把BeanPostProcessor注册到BeanFactory中；
      			beanFactory.addBeanPostProcessor(postProcessor);
            =======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程========

            AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor
    														postProcessBeforeInstantiation
    														postProcessAfterInstantiation
    	4.finishBeanFactoryInitialization(beanFactory);完成beanFactory初始化，完成剩下的单实例
    		1.遍历获取容器中的所有Bean，依次创建对象getBean(beanName)
    			getBean->doGetBean()->getSingleton()->
    		2.创建bean
    			【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截，InstantiationAwareBeanPostProcessor，会调用postProcessBeforeInstantiation()】
    			getSingleton(beanName)
    			1.先从缓存中获取当前bean，如果能获取，说明bean之前被创建过，直接使用，否则再创建
    				只要创建好的bean都会被缓存起来
    
    			2.createBean()创建bean
    				AnnotationAwareAspectJAutoProxyCreator 会在任何bean创建之前先尝试返回bean的实例
    				【BeanPostProcessor是在Bean对象创建完成初始化前后调用的】
    				【InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的】
    				1.resolveBeforeInstantiation(beanName, mbdToUse);解析BeforeInstantiation
    					希望后置处理器在此能返回一个代理对象；如果能返回代理对象就使用，不能就继续
    					bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
     					if (bean != null) {
     						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
                      }
                  2.doCreateBean(beanName, mbdToUse, args);真正的去创建一个bean实例；和3.5流程一样；
    
####    AnnotationAwareAspectJAutoProxyCreator 【InstantiationAwareBeanPostProcessor】的作用：
    	1.每个bean创建之前，调用postProcessBeforeInstantiation（）
     		关注MathCalculator和LogAspect的创建
     		1.判断当前bean是否在	advisedBeans 中（保存了所有需要增强的bean）
     		2.判断当前bean是否是基础类型的	Advice,Pointcut,Advisor,AopInfrastructureBean
     			或者是否是切面（@Aspect)
    		3.是否需要跳过	shouldSkip()
    			1，获取候选的增强器（切面里面的通知方法）【List<Advisor> candidateAdvisors = findCandidateAdvisors();】
    				每一个封装的通知方法的增强器是	InstantiationModelAwarePointcutAdvisor
    				判断每一个增强器是否是	AspectJPointcutAdvisor类型的;	返回true
    			2.返回false
    	2.创建对象
    		postProcessAfterInitialization
    			return wrapIfNecessary(bean, beanName, cacheKey);
    			wrapIfNecessary：如果bean适合被代理，则需要封装指定bean
    		1.获取当前bean的所有增强器（通知方法） Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
    			1.找到候选的所有增强器（找哪些通知方法是需要切入当前bean的方法的）
    			2.获取到能在bean使用的增强器
    			3.增强器排序
    		2.保存当前bean在advisedBeans中
    		3.如果当前bean需要增强，创建当前bean的代理对象
    		Object proxy = createProxy(
     					bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
     			1.获取所有增强器（通知方法）
     			2.保存到proxyFactory中
     			3.创建代理对象：spring自动决定
     				JdkDynamicAopProxy		jdk动态代理
     				ObjenesisCglibAopProxy	cglib的动态代理
    			4.给容器中返回当前组件使用cglib增强了的代理对象
    			5.以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
    
    	3.目标方法执行
    		容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器，目标对象，xx)
    		1.CglibAopProxy.intercept()	拦截目标方法的执行
    		2.根据ProxyFactory对象获取将要执行的目标方法连接器链
    		List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
    			List<Object> interceptorList保存所有的拦截器  5(1个默认的ExposeInvocationInterceptor 和4个增强器)
    			遍历所有的增强器，将其转为Interceptor
    			MethodInterceptor[] interceptors = registry.getInterceptors(advisor);
    			  将增强器转为List<MethodInterceptor>
    				如果是MethodInterceptor,直接添加到集合中
    				如果不是则使用AdvisorAdapter将增强器转为MethodInterceptor
    				转换完成返回MethodInterceptor[]
    		3.如果没有拦截器链	，直接执行目标方法。
    			拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制
    		4.如果有拦截器链，把需要执行的目标对象，目标方法，
    			拦截器等信息传入创建一个CglibMethodInvocation对象,
    			并执行mi.proceed()
    			Object retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
    		5.拦截器触发的过程
    			1.如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法
    			2.链式获取每一个拦截器，拦截器执行invoke()，每一个拦截器等待下一个拦截器执行完成返回以后再来执行。
    				拦截器链的机制，保证通知方法与目标方法的执行顺序
    	CglibMethodInvocation.procced();
    		currentInterceptorIndex:记录当前拦截器执行的索引，从-1，每次执行procced,索引自增一次
    	procced(){invoke(MethodInvocation mi)}
    									invoke(this)	 this->ReflectiveMethodInvocation
    								////////
    	ExposeInvocationInterceptor															---------------------
     		|								|																	↑
     	  mi.proceed()						|													finally {invocation.set(oldInvocation);}
    		↓						////////																    |
     	AspectJAfterThrowingAdvice															---------------------
     		|								|																	↑
     	  mi.proceed()						|							【catch (Throwable ex) {invokeAdviceMethod(getJoinPointMatch(), null, ex)}异常通知】
    		↓						////////																	|
     	AfterReturningAdviceInterceptor														---------------------
     		|								|																	↑
     	  mi.proceed()						|															【this.advice.afterReturning返回通知】
    		↓ 						////////																	|
     	AsepctJAfterAdvice																	---------------------
     		|								|														            ↑
     	  mi.proceed()						|							【finally {invokeAdviceMethod(getJoinPointMatch(), null, null);}后置通知】
    		↓ 						////////																	|
     	AspectJAroundAdvice					|												---------------------
     		|								|														            ↑
     	invokeAdviceMethod					|																	|
     	->invokeAdviceMethodWithGivenArgs	|														            |
     	->this.aspectJAdviceMethod.invoke	|														            |
    		↓ 						////////																	|
     	MethodBeforeAdviceInterceptor    																		|
     		--> 【this.advice.before前置通知】 --> mi.proceed() invokeJoinpoint()		--> method.invoke(target, args)

###     总结：
    	1.@EnableAspectJAutoProxy 开启AOP功能
    	2.@EnabelAspectJAutoProxy会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
    	3.AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
    	4.容器的创建流程：
    		1.registerBeanPostProcessors()注册后置处理器；创建AnnotationAwareAspectJAutoProxyCreator对象
    		2.finishBeanFactoryInitialization()初始化剩下的单实例bean
    			1.创建业务逻辑组件和切面
    			2.AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
    			3.组件创建完毕后，判断组件是否需要增强
    				是：切面的通知方法，包装秤增强器（Advisor）；给业务逻辑组件创建一个代理对象（cglib）
    	5.执行目标方法：
    		1.代理对象执行目标方法
    		2.CglibAopProxy.intercept()
    			1.得到目标方法的拦截器链（增强器包装成拦截器MethodInterceptor）
    			2.利用拦截器的链式调用机制，一次进人每个拦截器进行执行
    			3.效果
    		版本不同执行顺序存在差异  spring5
    				正常执行： 环绕通知=》前置通知=》目标方法=》后置通知=》返回通知
    				异常执行： 环绕通知=》前置通知=》目标方法=》后置通知=》异常通知

##  声明式事务
    
###     环境搭建
     1.导入相关依赖	数据源、数据库驱动、spring-jdbc模块
     2.配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
     3.给方法上标注@Transactional 表示当前方法是一个事务方法
     4.@EnabelTransactionManagement开启基于注解的事务管理功能
     		@Enabelxxx
     5.配置事务管理器来控制事务
     	@Bean
    	public PlatformTransactionManager transactionManager()
    
###     原理：
    	1.@EnabelTransactionManagement
     		利用TransactionManagementConfigurationSelector给容器中导入组件
     		导入两个组件
     			AutoProxyRegistrar
     			ProxyTransactionManagementConfiguration
    	2.AutoProxyRegistrar：
     		给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件
     		InfrastuctureAdvisorAutoProxyCreator:
     			利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用
    	3.ProxyTransactionManagementConfiguration
    		1.给容器中注册事务增强器
    			1.事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事务注解
    			2.事务拦截器：
    				TransactionInterceptor：保存了事务属性信息，事务管理器
    				是一个MethodInterceptor
    				在目标方法执行的时候：
    					执行拦截器链
    					事务拦截器
    						1.先获取事务相关属性
    						2.再获取TransactionManager，如果事先没有添加指定任何transactionmanager
    							最终会从容器中按照类型获取一个再获取TransactionManager
    						3.执行目标方法
    							如果异常，获取到事务管理器，利用事务管理回滚操作
    							如果正常，利用事务管理器，提交事务


##  扩展原理：
    BeanPostProcessor：bean后置处理器，bean创建对象初始化前后进行拦截工作
###    1.BeanFactoryPostProcessor: beanFactory的后置处理器
    在beanFactory标准初始化之后调用；来定制和修改BeanFactory的内容；
    所有的bean定义已经保存加载到beanFactory，但是bean的实例还未创建

    BeanFactoryPostProcessor原理：
        1. ioc容器创建对象
        2. invokeBeanFactoryPostProcessors(beanFactory);
            如何找到所有的BeanFactoryPostProcessor并执行他们的方法：
                1.直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
                2.在初始化创建其他组件前面执行

### 	2.BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 		postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
		在所有bean定义信息将要被加载，bean实例还未被创建

        	优先于BeanFactoryPostProcessor执行
        	利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件
    原理：
        1.ioc创建对象
        2.refresh()-》invokeBeanFactoryPostProcessors(beanFactory);
        3.从容器中获取所有的BeanDefinitionRegistryPostProcessor组件
            1.依次触发所有的postProcessBeanDefinitionRegistry()方法
            2.再来触发postProcessBeanFactory()方法BeanFactoryPostProcessor

        4.再从容器中找到BeanFactoryPostProcessor组件；然后依次触发postProcessBeanFactory()方法

###    3.ApplicationListener：监听容器中发布的事件。事件驱动模型开发；
        public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
            监听ApplicationEvent及其子事件

    步骤：
        1.写一个监听器（ApplicationListener实现类）来监听某个事件（ApplicationEvent及其子类）
      @EventListener
        原理：使用EventListenerMethodProcessor处理器来解析方法上的@EventListener；
        2.把监听器加入容器
        3.只要容器中有相关事件的发布，我们就能监听到事件：
            ContextRefreshedEvent：容器刷新完成（所有bean都完成创建）会发布这个事件
            ContextClosedEvent：关闭容器时会发布此事件
        4.发布一个事件：
            applicationContext.publishEvent();

    原理：
        ContextRefreshedEvent、IOC_Ext$1[source=自定义发布事件]、ContextClosedEvent
        1.ContextRefreshedEvent：
            1.容器创建对象	refresh()
            2.finishRefresh();容器刷新完成会发布ContextRefreshedEvent事件
 		2.IOC_Ext$1[source=自定义发布事件]
        3.ContextClosedEvent 容器关闭
            3.publishEvent(new ContextRefreshedEvent(this));
                【事件发布流程】：
                    1.获取事件多播器（派发器）：getApplicationEventMulticaster()
                    2.multicastEvent(applicationEvent, eventType);派发事件
                    3.getApplicationListeners()获取所有的ApplicationListener
                        for (ApplicationListener<?> listener : getApplicationListeners(event, type)) {
                        1.如果有Executor，可以支持使用Executor进行异步派发
                            Executor executor = getTaskExecutor();
                        2.否则，同步的方式直接执行listener方法：invokeListener(listener, event);
                            listener.onApplicationEvent(event);
                            拿到listener回调onApplicationEvent

    【事件多播器（派发器）】：
        1.容器创建对象：refresh（）
        2.initApplicationEventMulticaster();初始化ApplicationEventMuticaster
            1.先从容器中找有没有id是applicationEventMulticaster的组件
            2.如果没有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
                并加入到容器中，我们就可以在其他组件需要派发事件时，自动注入这个applicationEventMulticaster

    【容器中有哪些监听器】
        1.容器创建对象：refresh()
        2.registerListeners();
            从容器中拿得到所有的监听器，把他们注册到applicationEventMulticaster中
            String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
            //将listerner注册到ApplicationEventMulticaster中
            getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);

    public class EventListenerMethodProcessor
        implements SmartInitializingSingleton, ApplicationContextAware, BeanFactoryPostProcessor

 	SmartInitializingSingleton	原理： afterSingletonsInstantiated()
 		1.ioc容器创建对象并refresh()
 		2.finishBeanFactoryInitialization(bean);初始化剩下的单实例bean
 			1.先创建所有的单实例bean； getBean()
 			2.获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型的
 				如果是就调用afterSingletonInstantiated()