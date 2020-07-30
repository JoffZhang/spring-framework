package com.joffzhang.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zy
 * @date 2020/7/10 14:38
 */
public class MainStart {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		Persion bean = applicationContext.getBean(Persion.class);
		System.out.println(bean);
	}

	@Configuration
	static class AppConfig{

		@Bean
		public Persion getPersion(){
			return new Girl();
		}
	}

	interface Persion{

	}
	static class Girl implements Persion{

	}
}
