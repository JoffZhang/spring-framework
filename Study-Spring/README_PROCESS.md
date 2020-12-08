```
AnnotationConfigApplicationContext 
     extends GenericApplicationContext implements AnnotationConfigRegistry

new AnnotationConfigApplicationContext(xxx)
    AnnotationConfigApplicationContext父类GenericApplicationContext
    创建beanFactory【DefaultListableBeanFactory】
    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

```     
     
#Spring容器的refresh()【创建刷新】
    
##  1.prepareRefresh();刷新前的预处理（刷新上下文环境）
        1.initPropertySources();
            初始化一些属性设置；子类自定义个性化属性设置方法
        2.getEnvironment().validateRequiredProperties();
            检验属性的合法等
        3.this.earlyApplicationEvents = new LinkedHashSet<>();
            创建该集合（该集合作用：保存容器中的一些早期的事件）
##  2.ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();获取beanFactory
        1.refreshBeanFactory();刷新BeanFactory(不同类型实现不一样)
            注解方式此处设置id;this.beanFactory.setSerializationId(getId())
        2.getBeanFactory();返回GenericApplicationContext创建的beanFactory对象
            即【DefaultListableBeanFactory类型】的BeanFactory;         
##  3.prepareBeanFactory(beanFactory);BeanFactory的预准备工作（进行一些设置，功能填充）
        1.设置beanFactory的类加载器、表达式处理器、属性管理工具
        2.添加BeanPostProcessor【ApplicationContextAwareProcessor】
        3.设置忽略自动装配的接口EnvironmentAware、   EmbeddedValueResolverAware、..
        4.设置可解析的自动装配；我们可直接在任何组件中自动注入：
              BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext           
        5.添加BeanPostProcessor【ApplicationListenerDetector】
        6.添加编译时AspectJ支持
        7.给BeanFactory中注册一些能用的系统环境bean
            environment【ConfigurableEnvironment】
            systemProperties【Map<String, Object>】
            systemEnvironment【Map<String, Object>】
##  4.postProcessBeanFactory(beanFactory);BeanFactory准备工作完成后进行的后置处理工作
        1.子类通过重写这个方法来在BeanFactory创建并预准备完成后做进一步的设置
***
以上是BeanFactory的创建及预准备工作
***
         
##  5.invokeBeanFactoryPostProcessors(beanFactory);执行BeanFactoyPostProcessor的方法
    BeanFactoryPostProcessor：BeanFactory的后置处理器。在BeanFactory标准初始化之后执行的
    两个接口：BeanDefinitionRegistryPostProcessor 、BeanFactoryPostProcessor
    interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor

    执行BeanFactoryPostProcessor的方法：
    先执行BeanDefinitionRegistryPostProcessor
        1.获取所有BeanDefinitionRegistryPostProcessor
        2.先执行实现了PriorityOrdered优先级接口的BeanDefinitionRegistryPostProcessor
        invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry)
                            -》postProcessor.postProcessBeanDefinitionRegistry(registry);
        3.再执行实现了Ordered顺序接口的BeanDefinitionRegistryPostProcessor
        invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry)
                            -》postProcessor.postProcessBeanDefinitionRegistry(registry);
        4.最后执行其他没有实现任何优先级或者顺序接口的BeanDefinitionRegistryPostProcessor
        invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry)
                            -》postProcessor.postProcessBeanDefinitionRegistry(registry);
    再执行BeanFactoryPostProcessor
        1.获取所有常规BeanFactoryPostProcessor
        2.先执行实现了PriorityOrdered优先级接口的BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);
                            -》postProcessor.postProcessBeanFactory(beanFactory);
        3.再执行实现了Ordered顺序接口的BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);
                            -》postProcessor.postProcessBeanFactory(beanFactory);
        4.最后执行其他没有实现任何优先级或者顺序接口的BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);
                            -》postProcessor.postProcessBeanFactory(beanFactory);

##  6.registerBeanPostProcessors(beanFactory);注册BeanPostProcessor(Bean的后置处理器)【intercept bean creation】
        不同接口类型的BeanPostProcessor；在Bean创建前后执行的时机是不一样的
        BeanPostProcessor、
        DestructionAwareBeanPostProcessor、
        InstantiationAwareBeanPostProcessor、
        SmartInstantiationAwareBeanPostProcessor、
        MergedBeanDefinitionPostProcessor【internalPostProcessors】
        
        1.获取所有BeanPostProcessor;后置处理器默认可以通过PriorityOrdered、Ordered接口执行优先级
        2.先注册PriorityOrdered优先级接口的BeanPostProcessor
            把每个BeanPostProcessor添加到BeanFactory中
            registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors)        
                    -》beanFactory.addBeanPostProcessor(postProcessor);
        3.再注册Ordered接口的
        4.最后注册常规的
        5.最后注册所有内部MergedBeanDefinitionPostProcessor
        6.注册一个ApplicationListenerDetector；
            作用：在bean创建完成后检查是否是ApplicationListener
                 如果是则：this.applicationContext.addApplicationListener((ApplicationListener<?>) bean);
        
##  7.initMessageSource();初始化MessageSource组件（做国际化功能；消息绑定，消息解析 ）
        1.getBeanFactory()获取beanFactory
        2.看容器中是否有id为messageSource的，类型是MessageSource的组件
            如果有赋值给messageSource，如果没有则创建一个DelegatingMessageSource
                MessageSource：取出国际化配置文件中的某个key的值，能按照区域信息获取
        3.把创建好的MessageSource注册到容器中，以后获取国际化配置文件值的时候，可以自动注入MessageSource
            beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
        MessageSource.getMessage(String code, Object[] args, String defaultMessage, Locale locale);
##  8.initApplicationEventMulticaster();初始化事件派发器
        1.getBeanFactory()获取BeanFactory
        2.从BeanFactory中获取applicationEventMulticaster的ApplicationEventMulticaster
        3.如果没有配置，则创建一个SimpleApplicationEventMulticaster
        4.将创建的ApplicationEventMulticaster添加到BeanFactory中，以后其他组件直接自动注入
              
##  9. onRefresh();留给子容器（子类）初始化其他bean
        1.子类重写该方法，在容器刷新的时候可以自定义逻辑;       
##  10.registerListeners();在所有注册的bean中查找ApplicationListener（listener bean），并注册到消息广播中
        1.从容器中拿到所有ApplicationListener
        2.将每个监听器添加到事件派发器中：
               getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);     
        3.派发之前步骤产生的事件
        
##  11.finishBeanFactoryInitialization(beanFactory);初始化所有剩下的单实例bean
        1.beanFactory.preInstantiateSingletons();初始化剩下的单实例bean
            1.获取容器中的所有bean，依次进行初始化
            2.获取Bean的定义信息，RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
            3.Bean不是抽象、懒加载，单例的
                1.判断是否是FactoryBean; 是否实现了FactoryBean接口的bean
                2.不是工厂bean，利用getBean(beanName);创建对象
                    0.getBean(beanName);ioc.getBean();
                    1.doGetBean(name, null, null, false)
                    2.先获取缓存中保存的单实例Bean.如果能获取到说明这个Bean之前被创建过（所有创建过的单实例bean都会被缓存起来）
                        从private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);获取；
                    3.缓存中获取不到，开始Bean的创建对象流程
                    4.标记当前bean已经被创建alreadyCreated
                    5.获取Bean定义信息final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
                    6.【获取当前Bean依赖的其他Bean；如果有按照getBean()把依赖的Bean先创建出来】
                    7.启动单实例Bean创建流程
                        1.createBean(beanName,mbd,args);
                        2.Object bean = resolveBeforeInstantiation(beanName, mbdToUse);让BeanPostProcessors有机会返回一个代理bean实例，实例化前的前置处理
                            【InstantiationAwareBeanPostProcessor】提前执行
                            先触发：        postProcessBeforeInstantiation()
                            如果有返回值：   触发postProcessAfterInitialization()
                        3.如经过前置处理后返回代理结果不为空，直接返回该bean，否则执行 （4...）
                        
                        4.Object beanInstance = doCreateBean(beanName, mbdToUse, args);创建bean
                            1.【创建Bean实例】instanceWrapper = createBeanInstance(beanName, mbd, args);
                                利用工厂方法或者对象的构造器创建出Bean实例
                            2.applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
                                调用MergedBeanDefinitionPostProcessor的postProcessMergedBeanDefinition(mbd, beanType, beanName)
                            3.【bean属性填充】populateBean(beanName, mbd, instanceWrapper);  
                                赋值前：
                                1.拿到InstantiationAwareBeanPostProcessor后置处理器
                                    postProcessAfterInstantiation()
                                2.拿到InstantiationAwareBeanPostProcessor后置处理器
                                    postProcessProperties()
                                =======赋值之前==================
                                3.应用Bean属性的值；为属性利用setter方法等进行赋值
                                    applyPropertyValues(beanName, mbd, bw, pvs);
                            4.【Bean初始化】initializeBean(beanName, exposedObject, mbd)
                                1.【执行Aware接口方法】invokeAwareMethods(beanName, bean);执行xxxAware接口的方法
                                    BeanNameAware、BeanClassLoaderAware、BeanFactoryAware
                                2.【实例化前应用后置处理器】applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
                                    BeanPostProcessor.postProcessBeforeInitialization
                                3.【执行初始化方法】invokeInitMethods(beanName, wrappedBean, mbd);
                                    1.是否是InitializingBean接口的实现；执行接口规定的初始化；
                                    2.是否自定义初始化方法
                                4.【实例化后执行后置处理器】applyBeanPostProcessorsAfterInitialization
                                     BeanPostProcessor.postProcessAfterInitialization
                                5.注册Bean的销毁方法
                             5.将创建的Bean添加到缓存中singletonObjects
                         IOC容器就是这些Map；很多的Map保存了单实例Bean，环境信息...
                     所有Bean都利用getBean创建完成以后
                        检查所有的Bean是否是SmartInitializingSingleton接口的；如果是：就执行smartSingleton.afterSingletonsInstantiated();
##  12.finishRefresh();完成BeanFactory的初始化创建工作；IOC容器创建完成
        1.initLifecycleProcessor();初始化和生命周期有关的后置处理器；Lifecycle
            默认从容器中找是否有lifecycleProcessor的组件【LifecycleProcessor】；如果没有new DefaultLifecycleProcessor();
            加入到容器；
            
            写一个LifecycleProcessor的实现类，可以在BeanFactory
                void onRefresh();
                void onClose();	
        2.getLifecycleProcessor().onRefresh();
            拿到前面定义的生命周期处理器（BeanFactory）；回调onRefresh()
        3.publishEvent(new ContextRefreshedEvent(this));发布容器刷新完成时间
        4.LiveBeansView.registerApplicationContext(this);
        
   
***
#   总结
1.  Spring容器在启动的时候，先会保存所有注册进来的BeanDefinition定义信息
    1.  xml注册Bean
    2.  注解注册Bean @Bean ...
2.  Spring容器会合适的实际创建这些Bean
    1.  用到这个bean的时候；利用getBean创建bean；创建好以后保存到容器中
    2.  统一创建剩下所有的bean的时候，finishBeanFactoryInitialization()
3.  后置处理器;BeanPostProcessor
    1.  每个bean创建完成，都会使用各种后置处理器进行处理；来增强bean的功能
        AutowiredAnnotationBeanPostProcessor处理自动注入
        AnnotationAwareAspectJAutoProxyCreator来做AOP功能
        xxx
        增强的功能注解：
          AsyncAnnotationBeanPostProcessor
        ...
4.  事件驱动模型
    ApplicationListener事件监听
    ApplicationEventMulticaster事件派发
            