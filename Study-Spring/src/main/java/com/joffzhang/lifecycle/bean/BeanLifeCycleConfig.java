package com.joffzhang.lifecycle.bean;

import com.joffzhang.PrintLog;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 * @date 2021/2/3 11:25
 */
@Configuration
@ComponentScan("com.joffzhang.lifecycle.bean")
public class BeanLifeCycleConfig {


	@Bean(initMethod = "demoInit",destroyMethod = "demoDestroy")
	public DemoBean demoBean(){
		DemoBean demoBean = new DemoBean();
		demoBean.setName("初始化name = DEMO_BEAN");
		demoBean.setAge(1);
		return demoBean;
	}

}
