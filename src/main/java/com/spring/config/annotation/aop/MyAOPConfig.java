package com.spring.config.annotation.aop;


import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
/**
 * 注解版AOP配置开发
 * 【AOP】：动态代理
 * 		指在程序运行过程中动态地将某段代码切换到将指定方法的指定位置上进行执行的编程方法
 * 1、导入AOP模块（spring-aspects）
 * 2、定义一个业务逻辑类（MyDiv）定义在逻辑执行之前、之后、执行结束、正常返回、发生异常等的日志通知
 * 3、定义一个切面类（MyAspect）用于感知MyDiv.div(int i,int j)执行到那一步，进行监控信息的输出
 * 		通知方法：
 * 		前置通知(@Before)：aspectBefore在div方法执行之前运行
 * 		后置通知(@After)：aspectAfter在方法div执行之后进行调用（无论是否发生异常都会执行）
 * 		返回通知(@AfterReturning)：aspectReturn在方法div正常结束时执行
 * 		异常通知(@AfterThrowing)：aspectException在方法div发生异常时执行
 * 4、给切面方法标注何时执行 
 * 5.将切面类和切面监控的逻辑类都加入到IOC容器	
 * 6、在切面类上添加@Aspect注解，告知spring容器这个类时一个切面类
 * 【7】、在配置类上加注解@EnableAspectJAutoProxy，默认开启切面代理,相当于注解下的<aop:aspectj-autoproxy/>开启注解的AOp模式
 * 
 * AOP原理：【给容器中注入了什么组件、组件什么时候工作、做了那些事情】
 * @EnableAspectJAutoProxy：
 * 1、@EnableAspectJAutoProxy是什么？
 * 		类上有@Import(AspectJAutoProxyRegistrar.class)注解
 * 		利用AspectJAutoProxyRegistrar自定义给容器中注入Bean
 * 		internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator()
 *		给容器中注入AnnotationAwareAspectJAutoProxyCreator
 * 2、AnnotationAwareAspectJAutoProxyCreator：实现了后置处理器（BeanPostProcessor：在Bean初始化前后进行工作的）
 * 
 * 		AnnotationAwareAspectJAutoProxyCreator=》AspectJAwareAdvisorAutoProxyCreator=》AbstractAdvisorAutoProxyCreator
 * 				=》AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor（后置处理器）, BeanFactoryAware {}
 * 				关注Bean的后置处理器的相关方法和自动装配BeanFactory
 * 
 * 		a)AbstractAutoProxyCreator方法：
 * 			AbstractAutoProxyCreator.setBeanFactory(BeanFactory)
 * 			AbstractAutoProxyCreator.postProcessBeforeInstantiation(Class<?>, String)
 * 			postProcessAfterInitialization(Object, String)
 * 		b)AbstractAdvisorAutoProxyCreator方法
 * 			AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory)(重写了其父类的方法)
 * 			AbstractAdvisorAutoProxyCreator.initBeanFactory
 * 		c)AnnotationAwareAspectJAutoProxyCreator
 * 			AnnotationAwareAspectJAutoProxyCreator.initBeanFactory
 * 流程：
 * 1、创建IOC容器
 * 2、注册配置类调用refresh（）方法，刷新容器
 * 3、registerBeanPostProcessors(beanFactory);注册bean的后置处理器，方便拦截bean的创建
 * 		1、获取ioc容器中已经定义了的需要创建的所有的BeanPostProcessor
 * 		2、给容器中添加别的BeanPostProcessor
 * 		3、对所有BeanPostProcessor进行处理，比如优先级排序，按优先级进行区分和注册。优先注册PriorityOrder接口的BeanPostProcessor
 * 		4、再注册实现order的BeanPostProcessor
 * 		5、没实现优先级的BeanPostProcessor
 * 		6、创建BeanPostProcessor，实际上就是创建BeanPostProcessor对象，保存再容器中
 * 			创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 * 			a)创建Bean的实例
 * 			b）populateBean（beanName,mdb,instanceWapper）;给Bean赋值
 * 			C）initializeBean初始化Bean
 * 				invokeAwareMethods(final String beanName, final Object bean)处理Aware接口方法回调
 * 				applyBeanPostProcessorsBeforeInitialization(wrappedBean,beanName)应用后置处理器的processorsBeforeInitialization方法拿到所有的后置处理器
 * 			d)invokeInitMethods()执行之定义的初始化方法
 * 			e)applyBeanPostProcessorsAfterInitialization(wrappedBean,beanName)processorsAfterInitialization方法拿到所有的后置处理器
 * 			BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功，放入到internalPostProcessor集合中
 * 		7、把BeanPosterProcessor中
 * 			beanFactory.addBeanPosterProcessor(postProcessor)
 * =====以上是注册和创建AnnotationAwareAspectJAutoProxyCreator过程======
 * 作为后置处理器AnnotationAwareAspectJAutoProxyCreator的作用
 * 4、finishBeabFactoryInitialization(factory)来完成BeanFACTO的初始化操作，创建剩下的单实例Bean
 * 
 * 【创建BEAN】
 * 【结论】：AnnotationAwareAspectJAutoProxyCreator在所有Bean的创建之前做一个拦截，InstantiationAwareBeanPostProcessor会调用postProcessBeforeInstantiation(Class<?> beanClass, String beanName)方法
 * [BeanPosterProcessor实在BEAN创建完成前后调用的]
 * [InstantiationAwareBeanPostProcessor是在创建Bean实例之前先尝试用后置处理器返回对象的]在任何Bean创建之前先尝试返回Bean的实例
 * 		1）遍历容器中的所有Bean，依次创建对象getBean（beanName）
 * getBean=》doGetBean=》getSingleton
 * 		2）先从缓存中或取Bean如果存在直接调用，如果不存在则创建。有就用，没有就创建。只要创建好的Bean就会被缓存起来
 * 			1）resolveBeforeInstantiation(beanName, mbdToUse);希望后置处理器能返回一个代理对象。如果能返回代理对象就使用，
 * 
 * 				
 * 				）后置处理器尝试返回对象
 * 				applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 * 				判断拿到的后置处理器的类型，如果是InstantiationAwareBeanPostProcessor，就执行后置处理器的postProcessBeforeInstantiation方法
 * 				if (bean != null) {
						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
					}
 * 			2）如果不能创建，调用doCreateBean(beanName, mbdToUse, args);创建一个实例Bean（这才是真正创建的Bean实例和3.6的过程程相同）
 * 5、AnnotationAwareAspectJAutoProxyCreator的InstantiationAwareBeanPostProcessor的作用？
 * 	1、在创建所有的Bean之前，调用postProcessBeforeInstantiation方法
 * 	再关心我们所写的Div和Aspect的创建
 * 	a）判断当前Bean是否再adviceBean中，即是否属于增强的BEAN
 * 	b）判断当前Bean是否属于advice、pointCut、Advisior、AopInfrastructureBean基础类型或者是切面类型的
 * 
 * 	c）	是否需要跳过
 * 	1、获取候选增强器（切面里面的通知方法），（List<Advisor> candidateAdvisors）
 * 	每一个封装通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 * 	判断每一个增强器是否是AspectJPointcutAdvisor类型的，是就返回true，而我们的增强器是InstantiationModelAwarePointcutAdvisor类型的，返回false
 * 	2永远返回false
 * 	
 * 5、postProcessAfterInstantiation(Class<?> beanClass, String beanName)方法做了什么事情？
 * 	return wrapIfNecessary(bean, beanName, cacheKey);如果需要的话进行包装
 * 		1获取当前Bean的所有增强器（通知方法）所有的增强器封装称Object[] specificInterceptors数组
 * 			找到能再当前Bean的通知方法（找哪些通知方法需要切入到当前Bean方法的）
 * 			获取能再Bean使用的增强器
 * 			给增强器进行排序
 * 		2、把当前Bean放在到advisedBeans中，使用this.advisedBeans.put(cacheKey, Boolean.TRUE);
 * 		3、创建代理对象createProxy
 * 			获取到所有的增强器（Advisor）
 * 			保存到ProxyFactory中
 * 			创建代理对象，（由程序自动选择）一种是jdk形式的、一种是cglib形式的代理
 * 		wrapIfNecessary(bean, beanName, cacheKey)给容器中返回当前组件使用cglib增强了的代理对象
 * 		以后容器中拿到的就是组件的代理对象，执行目标方法的时候就会执行通知方法的流程
 * 
 * 目标方法的执行
 * 容器中保存组件代理对向，就是cglib增强后的对象，这个对象保存了详细的信息
 * 方法跳到CglibAopProxy的intercept方法；拦截目标方法的执行
 * 1、List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);获取目标方法拦截起的拦截链
 * 2、如果没有拦截器链，就直接执行目标方法，
 * 		拦截器链，每一个通知方法被包装为方法拦截器，利用MethodInterceptor机制进行执行，
 * 3、如果有连接器链，retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 * 		创建一个CglibMethodInvocation对象，并且传入方法的相关属性参数调用proceed()方法
 * ===============拦截器链是如何或取的===============================
 * List<Object> interceptorList = new ArrayList<Object>(config.getAdvisors().length);
 * 创建List来保存所有的拦截器，list的默认长度为增强器的数量
 * 
 * Interceptor[] interceptors = registry.getInterceptors(advisor);遍历所有增强器，将器转为Interceptor
 * List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>(3);
 * 如果是MethodInterceptor加入到集合中，如果不是，使用AdvisorAdapter将增强其转为MethodInterceptor，然后返回转换完成的数组
 * 
 * 
 * ==================================================================
 * 
 * 4、拦截器链的触发过程
 * 	1）如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1的大小一样（指行到了最后一个拦截器，）执行目标方法
 * 	2）链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截等待下一个拦截器执行完成返回以后再来执行
 * 		拦截器链的机制，保证通知方法与目标方法的执行顺序，参见图片
 * ==================aop总结=====================
 * @EnableAspectJAutoProxy开启注解事务
 * @EnableAspectJAutoProxy给容器中注入AnnotationAwareAspectJAutoProxyCreator
 * AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 * 
 * 容器的创建流程
 * 	1、registerBeanPostProcessors注册创建后置处理器，创建AnnotationAwareAspectJAutoProxyCreator
 * 	2、finishBeabFactoryInitialization初始化剩下的单利Bean
 * 		创建业务逻辑组件和切面组件
 * 		AnnotationAwareAspectJAutoProxyCreator拦截创建过程
 * 		组件创建完成后，判断组件是否需要增强
 * 			是;切面的通知方法，包装成增强器，给业务逻辑组件创建一个代理对象（cglib）
 * 		
 * 	3、执行目标方法
 * 		代理对象执行目标方法
 * 		CglibAopProxy.intercept();
 * 			1、得到目标方法的拦截器链（增强其包装成的拦截器）
 * 			2、利用拦截器的链式机制，依次进入每一个拦截器执行
 * 			3、效果
 * 				前置通知=》目标方法=》后置通知=》放回通知
 * 				前置通知=》目标方法=》后置通知=》异常通知
 */

@Configuration
@EnableAspectJAutoProxy
public class MyAOPConfig {
	//将pojo注入到容器中
	@Bean
	public MyAspect myAspect() {
		return new MyAspect();
	}
	
	@Bean
	public MyDiv myDiv() {
		return new MyDiv();
	}
}
