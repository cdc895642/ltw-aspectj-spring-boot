package com.test.ltwaspectjtest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
//@Component
public class ProfilingAspect {

  @Around("methodsToBeProfiled()")
  public Object profile(ProceedingJoinPoint pjp) throws Throwable {
    StopWatch sw = new StopWatch(getClass().getSimpleName());
    try {
      sw.start(pjp.getSignature().getName());
      return pjp.proceed();
    } finally {
      sw.stop();
      System.out.println(sw.prettyPrint());
    }
  }

  @Pointcut("execution(public * com.test.ltwaspectjtest.service..*(..))")
  public void methodsToBeProfiled(){}
}