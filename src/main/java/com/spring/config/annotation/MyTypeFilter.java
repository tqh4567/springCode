package com.spring.config.annotation;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
/**
 * ComponentScan自定义的过滤规则
 * @author tqh4567
 *
 */
public class MyTypeFilter implements TypeFilter {
	//MetadataReader：读取当前扫描类的信息
	//MetadataReaderFactory：加载MetadataReader（或者读取其子类或者父类）的工厂
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		//读取基础类的完整注释元数据，包括注释方法的元数据
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		//读取基础类的基本类元数据
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		//返回类文件的相关资源（类的路径）
		Resource resource = metadataReader.getResource();
		
		String name = classMetadata.getClassName();
		if(name.contains("er")) {
			return true;
		}
		System.out.println("----->"+name);
		return false;
	}

}
