package com.nhnacademy.koreawatersupplybillservice.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Log log = LogFactory.getLog(LoggingAspect.class);

    @Around("execution(public * load(..))")
    public Object loadLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logTime(pjp);
    }


    @Around("execution(public * calculateFee(..))")
    public Object calculateFeeLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logTime(pjp);
    }

    @Around("execution(public * parse(..))")
    public Object parseLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logTime(pjp);
    }

    @Around("execution(public * report(..))")
    public Object reportLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logTime(pjp);
    }

    @Around("execution(public * findFeesBaseOnUsage(..))")
    public Object findFeesBaseOnUsageLogging(ProceedingJoinPoint pjp) throws Throwable {
        return logTime(pjp);
    }

    private Object logTime(ProceedingJoinPoint pjp) throws Throwable {
        var stopWatch = new StopWatch();
        try {
            stopWatch.start();
            return pjp.proceed();
        } finally {
            stopWatch.stop();
            log.info(pjp.getSignature().getName());
            log.info(stopWatch.prettyPrint());
        }
    }
}
