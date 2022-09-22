package cn.edu.njtech.aspect;

import cn.edu.njtech.aspect.entity.RequestLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* cn.edu.njtech.seckill.web.*.*(..))")
    public void log() {

    }

    @Before(value = "log()")
    public void doBefore(JoinPoint joinPoint) {
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String url = requestAttributes.getRequest().getRequestURL().toString();
        String ip = requestAttributes.getRequest().getRemoteAddr();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request: {} ", requestLog);
    }
}
