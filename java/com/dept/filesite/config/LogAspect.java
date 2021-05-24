package com.dept.filesite.config;

import com.dept.filesite.utils.LogSqlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @className: LogAspect
 * @description: 切面类
 * @author: 201998
 * @create: 2020-10-19 16:00:00
 */

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    //切点名称
    @Pointcut("execution(public * com.dept.filesite.controller.*.*(..))")
    public void controllerLog(){}

    @Pointcut("execution(public * com.dept.filesite.mapper.*.*(..))")
    public void mapperLog(){}

    //环绕通知
    @Around("controllerLog()")
    public Object logAroundController(ProceedingJoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();

        logger.info("------URL: "+request.getRequestURL());
        logger.info("------PARAMS: "+ Arrays.toString(joinPoint.getArgs()));
        logger.info("------CLASS_METHOD: "+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());

        //请求的返回值
        Object result = null;
        try {
            result = joinPoint.proceed();
            ObjectMapper json = new ObjectMapper();
            logger.info("------RESULT: "+ json.writeValueAsString(result));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.info("------RESULT: ");
        }

        //要把响应值返回
        return result;
    }

    @Around("mapperLog()")
    public Object logAfterMapper(ProceedingJoinPoint joinPoint){

        logger.info("------SQL: "+ LogSqlUtil.getMybatisSql(joinPoint,sqlSessionFactory));

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
