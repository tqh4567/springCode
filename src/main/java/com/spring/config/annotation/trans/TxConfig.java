package com.spring.config.annotation.trans;



import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * 声明式事务的配置类
 * @author tqh4567
 *1、导入数据源、数据库连接、spring-jdbc模块使用jdbcTemplate进行数据库的操作
 *2、配置数据源和jdbcTemplate（spring的简化数据操作）操作数据库
 *3.给业务层加上@Transactional注解，标注为事务方法
 *4.再配置类上添加@EnableTransactionManagement开启事务
 *5.最关键的一步，配置事务管理器来管理事务PlatformTransactionManager
 *
 	@Bean
	public PlatformTransactionManager platformTransactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}
 *	
 *==========================@EnableTransactionManagement原理=============================================
 *@EnableTransactionManagement使用@Import(TransactionManagementConfigurationSelector.class)
 *
 *TransactionManagementConfigurationSelector继承AdviceModeImportSelector<EnableTransactionManagement>
 *
  AdviceModeImportSelector<A extends Annotation> implements ImportSelector
  
 *AdviceModeImportSelector<EnableTransactionManagement>实现ImportSelector接口
 *
 *1、@EnableTransactionManagement
 *		AdviceMode mode() default AdviceMode.PROXY;
 *2、TransactionManagementConfigurationSelector
 *		protected String[] selectImports(AdviceMode adviceMode) {
		switch (adviceMode) {
			case PROXY:
				return new String[] {AutoProxyRegistrar.class.getName(), ProxyTransactionManagementConfiguration.class.getName()};
		}
		而@EnableTransactionManagement中的AdviceMode mode()默认的就是PROXY形式的，所以会返回AutoProxyRegistrar.class.getName(), ProxyTransactionManagementConfiguration.class.getName()
		导入AutoProxyRegistrar和ProxyTransactionManagementConfiguration两个组件
		=====================================================================
		AutoProxyRegistrar：给容器中注入一个InfrastructureAdvisorAutoProxyCreator
				InfrastructureAdvisorAutoProxyCreator功能？
				利用后置处理器机制再对象创建以后，包装对象，返回一个代理对象，代理对象执行方法拦截器链进行调用
		======================================================================
		ProxyTransactionManagementConfiguration：	有@Configuration	注解，说明他是一个配置类，给容器中注入相关组件
		给容器中注入事务增强器
			事务增强器要用事务的注解信息。AnnotationTransactionAttributeSource解析事务的注解
			事务拦截器：TransactionInterceptor保存了事务的属性信息，事务管理器
					他是一个MethodInterceptor方法拦截器：在目标方法执行的时候，执行拦截器链
						1、先获取事务的属性，2、获取PlatformTransactionManager,如果试先没有添加任何TransactionManager
						最终就会从容器中按照类型获取一个PlatformTransactionManager
						3、执行目标方法，如果异常，获取食物管理器，利用事务进行回滚
									如果正常，利用食物管理器，利用食物进行提交
 */
@Configuration
@EnableTransactionManagement//开启事务
@ComponentScan({"com.spring.config.annotation.trans"})
public class TxConfig {
	//加载数据源
	@Bean
	public DruidDataSource dataSource(){
		DruidDataSource druidDataSource=new DruidDataSource();
		druidDataSource.setUsername("root");
		druidDataSource.setPassword("123456");
		druidDataSource.setUrl("jdbc:mysql://192.168.25.132:3306/crm1");
		druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return druidDataSource;
	}
	//有缓存，并不会重新创建一个DataSource；给容器中加组件的方法调用多次指会产生一个
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
		
	}
	@Bean
	public PlatformTransactionManager platformTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	

}
