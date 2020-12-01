package com.joffzhang.annotation.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author zy
 * @date 2020/11/30 17:18
 * 自定义逻辑返回需要导入的组件
 *
 * https://blog.csdn.net/zombres/article/details/107229854
 */
public class MyImportSelector implements ImportSelector {
	/**
	 * @param importingClassMetadata AnnotationMetadata 是注解启动类的上的注解元信息
	 * @return
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {

		return new String[]{"com.joffzhang.annotation.bean.Yellow"};
	}
	/**
	 * 可选的实现方法
	 * 返回一个谓词接口，该方法制定了一个对类全限定名的排除规则来过滤一些候选的导入类，默认不排除过滤。
	 * 	  @Override
	 *    public Predicate<String> getExclusionFilter() {
	 * 		return null;
	 *    }
	 */

}
