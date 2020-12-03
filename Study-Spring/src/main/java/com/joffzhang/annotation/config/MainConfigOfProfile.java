package com.joffzhang.annotation.config;

import com.joffzhang.annotation.bean.Blue;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Profile：
 * 		Spring为我们提供的可以根据当前环境，动态的激活和切换一系列组件的功能；
 *
 * 开发环境、测试环境、生产环境；
 * 数据源：(/A)(/B)(/C)；
 *
 *
 * @Profile：指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *
 * 1）、加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是default环境
 * 2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3）、没有标注环境标识的bean在，任何环境下都是加载的；
 * @author zy
 * @date 2020/12/2 10:45
 */

//@Profile("dev")
@PropertySource(value = "classpath:/dbconfig.properties")
@Component
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

	@Value("${db.user}")
	private String username;

	private String driverClass;


	@Profile("dev")
	@Bean
	public Blue blue(){
		return new Blue();
	}

	@Profile("dev")
	@Bean
	public DataSource dataSourceDev(@Value("${db.password}")String password) throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setUser(username);
		comboPooledDataSource.setPassword(password);
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.120.202:30779/tx_logger");
		return comboPooledDataSource;
	}
	@Profile("prod")
	@Bean
	public DataSource dataSourceProd(@Value("${db.password}")String password) throws PropertyVetoException {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setUser(username);
		comboPooledDataSource.setPassword(password);
		comboPooledDataSource.setDriverClass(driverClass);
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://192.168.120.202:30779/txcn-demo");
		return comboPooledDataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		driverClass = resolver.resolveStringValue("${db.driverClass}");
	}
}
