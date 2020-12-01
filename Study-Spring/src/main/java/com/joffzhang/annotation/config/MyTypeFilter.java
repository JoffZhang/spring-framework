package com.joffzhang.annotation.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义包扫描 - 规则
 * @author zy
 * @date 2020/11/30 14:16
 */
public class MyTypeFilter implements TypeFilter {
	/**
	 *确定此筛选器是否与所描述的类匹配给定的元数据
	 * @param metadataReader 目标类的元数据阅读器
	 * @param metadataReaderFactory 获取元数据读取器的工厂用于其他类(如超类和接口)
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		//读取基础类的完整注释元数据，包括带注释方法的元数据
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

		//阅读基础类的基本类元数据。
		ClassMetadata classMetadata = metadataReader.getClassMetadata();

		//获取当前类资源（类的资源）
		Resource resource = metadataReader.getResource();

		String className = classMetadata.getClassName();
		System.out.println(" metadataReader.getClassMetadata()getClassName()---->" + className);
		if(className.contains("er")){
			return true;
		}
		return false;
	}
}
