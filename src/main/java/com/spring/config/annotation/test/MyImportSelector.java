package com.spring.config.annotation.test;

import java.util.Set;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
	/**
	 * 创建自定义的导入选择规则
	 * 返回值为要导入容器的全类名
	 * AnnotationMetadata：当前标注@Import的所有注解信息，只要在类上的注解信息就都能获取到
	 */
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		//importingClassMetadata的相应方法
		//=========
		//获取类上的所有的标注信息，返回标注的全类名
		/*Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
		System.out.println(annotationTypes);*/
		
		//=========
		//方法不要返回null，会产生空指针异常，可以返回一个空的数组
		return new String[]{"com.spring.pojo.Yellow","com.spring.pojo.Blue"};
	}

}
