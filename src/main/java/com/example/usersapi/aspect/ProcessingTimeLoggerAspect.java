package com.example.usersapi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * The ProcessingTimeLoggerAspect class is an aspect that logs the processing time of methods
 * annotated with @LogProcessingTime annotation.
 * It uses AOP (Aspect-Oriented Programming) to intercept method invocations and measure the
 * time taken for their execution.
 */
@Aspect
@Component
public class ProcessingTimeLoggerAspect {

    /**
     * Logs the processing time of methods annotated with @LogProcessingTime.
     *
     * @param joinPoint The ProceedingJoinPoint representing the intercepted method invocation.
     * @return The result of the intercepted method invocation.
     * @throws Throwable If an exception occurs during the method invocation.
     * @see LogProcessingTime
     */
    @Around("@annotation(com.example.usersapi.aspect.LogProcessingTime)")
    public Object logProcessingTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;

        System.out.println("Method " + joinPoint.getSignature().getName() + " took " + processingTime + " ms");

        return result;
    }
}
