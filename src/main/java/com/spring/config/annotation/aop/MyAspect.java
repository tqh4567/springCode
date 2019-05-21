package com.spring.config.annotation.aop;



import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**指定某一个方法
 * @Before(value = "public int com.spring.config.annotation.aop.MyDiv.div(int, int)")
 * 指定所有的方法
 * @Before(value = "public int com.spring.config.annotation.aop.MyDiv.*(..)")
 *
 */
@Aspect
public class MyAspect {
	@Pointcut("execution(public int com.spring.config.annotation.aop.MyDiv.*(..))")
	public void pointCut() {
		
	}
	@Before(value = "pointCut()")
	public void aspectBefore(JoinPoint joinpoint) {
		System.out.println(joinpoint.getSignature().getName()+"执行之前@Before.......参数为{"+Arrays.asList(joinpoint.getArgs())+"}");
		
	}
	@After(value = "pointCut()")
	public void aspectAfter(JoinPoint joinpoint) {
		System.out.println(joinpoint.getSignature().getName()+"方法执行之后@After.......");
	}
	/**
	 * @AfterReturning(value = "pointCut()",returning="result")
		public void aspectReturn(Object result,JoinPoint joinpoint){}
		执行出错：java.lang.IllegalArgumentException: error at ::0 formal unbound in pointcut 
		
		@AfterReturning(value = "pointCut()",returning="result")
		public void aspectReturn(JoinPoint joinpoint,Object result){}
		执行成功
		
		@AfterReturning(value = "pointCut()",returning="result")
		public void aspectReturn(Object result){}
		执行成功
		为什么？
		JoinPoint必须出现在参数列表的第一位，否则spring也是无法进行识别的
		
	 * 
	 * @param result
	 * @param joinpoint
	 */
	@AfterReturning(value = "pointCut()",returning="result")
	public void aspectReturn(JoinPoint joinpoint,Object result) {
		System.out.println(joinpoint.getSignature().getName()+"方法执行返回@AfterReturning.......返回结果为{"+result+"}");
	}
	@AfterThrowing(value = "pointCut()",throwing="exception")
	public void aspectException(Exception exception) {
		System.out.println("DIV方法执行出现异常@AfterThrowing........异常信息为"+exception.getMessage());
	}
}
