package com.joffzhang.extension;

import com.joffzhang.annotation.bean.Dog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


/**
 * @author zy
 * @date 2021/1/27 10:18
 * {@link javax.annotation.PostConstruct}  不算扩展点，是一个标注，作用是在bean的初始化阶段，如果一个方法标注了 @PostConstruct,会先调用该方法。
 * 	触发点：在psotProcessBeforeInitialization之后，InitializaingBean.afterPropertiesSet之前
 *
 * 	使用场景：标注某一方法，来进行初始化某些属性
 */
@Configuration
@ComponentScan(value = "com.joffzhang.annotation.bean",includeFilters = {
			@Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {Dog.class})
},useDefaultFilters = false)
public class PostConstructAnnotation {

}
