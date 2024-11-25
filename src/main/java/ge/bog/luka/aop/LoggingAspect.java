package ge.bog.luka.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("within(@org.springframework.web.bind.annotation.RestController *) || @annotation(ge.bog.luka.aop.LoggingAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Call method: " + joinPoint.getSignature().getName() + ", Args: "
                + Arrays.toString(joinPoint.getArgs()));

        Object o = joinPoint.proceed();

        if (o != null) {
            log.info(joinPoint.getSignature().getName() + " response: " + o.toString());
        } else {
            log.info(joinPoint.getSignature().getName() + " completed with no return value.");
        }

        return o;

    }

    @AfterThrowing(pointcut = "within(@org.springframework.web.bind.annotation.RestController *) || @annotation(ge.bog.luka.aop.LoggingAnnotation)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("Exception thrown in method: " + joinPoint.getSignature().getName() +
                ", Exception message: " + exception.getMessage());
    }

}
