package top.dongfangbai.demomasterslavedatasource.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wyl
 * @date 2019/04/09
 * @description 控制层日志切面接口配置
 */
@Aspect
@Configuration
public class LogAopConfig {

    private final Logger log = LoggerFactory.getLogger(LogAopConfig.class);

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * top.dongfangbai.demomasterslavedatasource.controller.*.*(..))")
    public void logCut() {

    }

    @Before("logCut()")
    public void logStart(JoinPoint joinPoint) {
        log.info("====================接口日志记录开始====================");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 接口路径
        log.info("url = {}", request.getRequestURI());
        // 接口方法
        log.info("method = {}", request.getMethod());
        // ip地址
        log.info("ip = {}", request.getRemoteAddr());
        // 获得参数
        log.info("params = {}", joinPoint.getArgs());
        // 获得类方法
        log.info("classMethod = {}", joinPoint.getSignature().getDeclaringTypeName() + "#" + joinPoint.getSignature().getName());
    }
    @AfterReturning("logCut()")
    public void logEnd(){
        log.info("====================接口日志记录结束====================");
    }

    @Around("logCut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("接口用时：{}", endTime - startTime + "ms" + "  ----  " + result);
        return result;
    }
}
