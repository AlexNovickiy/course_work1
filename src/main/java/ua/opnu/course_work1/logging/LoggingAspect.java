package ua.opnu.course_work1.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* ua.opnu.springlab3.rest..*(..)) || execution(* ua.opnu.springlab3.service..*(..)) || execution(* ua.opnu.springlab3.repo..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* ua.opnu.springlab3.rest..*(..)) || execution(* ua.opnu.springlab3.service..*(..)) || execution(* ua.opnu.springlab3.repo..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "execution(* ua.opnu.springlab3.rest..*(..)) || execution(* ua.opnu.springlab3.service..*(..)) || execution(* ua.opnu.springlab3.repo..*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Exception in method: {} with cause: {}", joinPoint.getSignature(), error.getCause() != null ? error.getCause() : "NULL");
    }
}

