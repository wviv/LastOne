package com.wa.last.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @date 2019/6/6 11:15
 */
@Aspect
@Slf4j
@SuppressWarnings("unchecked")
public class ControllerHandler {

    public static Logger logger = LoggerFactory.getLogger("");
    public static String request = ".RequestParameter";


    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMethodPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMethodPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMethodPointcut() {
    }

    /**
     * post请求处理
     *
     * @param joinPoint
     */
//    @Before("postMethodPointcut()")
//    public void postMethodHandler(JoinPoint joinPoint) {
//        Map<String, Object> map = new LinkedHashMap<>();
//        logger.info(JSON.toJSONString(map));
//    }

    /**
     * get请求处理
     *
     * @param joinPoint
     */

    //TODO  暂时去掉
//    @Before("requestMethodPointcut()")
    public void getMethodHandler(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Map<String, Object> map = new LinkedHashMap<>();
        Class<?> declaringClass = method.getDeclaringClass();
        //类
        String className = declaringClass.getName();
        //方法
        String methodName = method.getName();
        //入参名称
        String[] parameterNames = signature.getParameterNames();
        if (parameterNames != null && parameterNames.length != 0) {
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < parameterNames.length; i++) {
                map.put(parameterNames[i], args[i]);
            }
        }
        logger = LoggerFactory.getLogger(className + "." + methodName);
        logger.info("parameters : " + JSON.toJSONString(map));
    }
}
