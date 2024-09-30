package com.visa.prj.orderapp.aop;

import com.visa.prj.orderapp.service.EntityNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.visa.prj.orderapp.service.*.*(..))")
    public void logBefore(JoinPoint jp) {
     logger.info("called " + jp.getSignature());
     Object[] args = jp.getArgs();
     for(Object arg: args) {
         logger.info("argument " + arg);
     }
    }

    @After("execution(* com.visa.prj.orderapp.service.*.*(..))")
    public void logAfter(JoinPoint jp) {
        logger.info("***********");
    }

    @Around("execution(* com.visa.prj.orderapp.service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
            Object ret = pjp.proceed(); // actual method
        long endTime = System.currentTimeMillis();
        logger.info("Time : " + (endTime - startTime) + " ms");
        return ret;
    }

    @AfterThrowing(value = "execution(* com.visa.prj.orderapp.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint jp, Exception ex) {
        logger.info("Exception :" + ex.getMessage());
    }
}
