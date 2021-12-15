package com.user.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrackExceutionTime {
	
	Logger logger=LoggerFactory.getLogger(TrackExceutionTime.class);

	//method to create track time of Exceution
	@Around("@annotation(com.user.aop.TrackExecutionTime)")
	public Object  trackTimeToExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		
		long startTime=System.currentTimeMillis();
		Object object = proceedingJoinPoint.proceed();
		long endTime=System.currentTimeMillis();
		logger.info("Method name is.."+proceedingJoinPoint.getSignature()+" "+"time taken to Execute."+(endTime-startTime));
		return object;
}
}
