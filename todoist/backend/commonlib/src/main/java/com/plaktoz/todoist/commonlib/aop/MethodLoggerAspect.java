package com.plaktoz.todoist.todoistapp.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodLoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(MethodLoggerAspect.class);

    @Around("execution(* com.plaktoz.todoist..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().toShortString();
        long start = System.currentTimeMillis();
        log.info("START {}", method);
        try {
            Object result = joinPoint.proceed();
            log.info("END {} ({} ms)", method, (System.currentTimeMillis() - start));
            return result;
        } catch (Throwable ex) {
            log.error("EXCEPTION in {}: {}", method, ex.getMessage(), ex);
            throw ex;
        }
    }
}