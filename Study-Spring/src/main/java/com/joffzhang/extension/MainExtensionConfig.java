package com.joffzhang.extension;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring容器中Bean的声明周期内扩展点的调用
 * @author zy
 * @date 2021/1/25 17:31
 */
@Configuration
@ComponentScan(value = "com.joffzhang.extension")
public class MainExtensionConfig {

}
