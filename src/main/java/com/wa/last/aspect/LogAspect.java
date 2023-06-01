package com.wa.last.aspect;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logPointCut() {

    }

    //处理完请求后执行
    @Around(value = "logPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        log.info("注意，这个模块有所操作");
        try {
            Object object = joinPoint.proceed();
            return object;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            log.info("ddd");
        }
    }

//    @Before("logPointCut()")
//    public void doBefore(){
//        log.info("before");
//    }
//
//    public void handleLog(final JoinPoint joinPoint,final Exception e, Object jsonResult){
//
//        Log controllerLog = getAnnotation(joinPoint);
//        if(controllerLog == null){
//            return;
//        }
//
//        //获取当前登录用户
//        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
//
//        //数据库日志
//        SysOperLog operLog = new SysOperLog();
//        operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
//
//        //请求的地址
//        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
//        operLog.setOperIp(ip);
//
//        //返回参数
//        operLog.setJsonResult(JSON.toJSONString(jsonResult));
//        operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
//
//        if(loginUser != null){
//            operLog.setOperName(loginUser.getUsername());
//        }
//
//        if(e != null){
//            operLog.setStatus(BusinessStatus.FAIL.ordinal());
//            operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
//        }
//
//        //设置方法名称
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        operLog.setMethod(className + "." + methodName);
//
//        //设置请求方式
//        operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
//
//        //处理设置注解上的参数
//        getControllerMethodDescription(joinPoint, controllerLog, operLog);
//        // 保存数据库
//        AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
//    }
//
//    //获取注解中对方法得描述信息
//    public void getControllerMethodDescription(JoinPoint joinPoint,Log log,SysOperLog operLog){
//
//        //设置action动作
//        operLog.setBusinessType(log.businessType().ordinal());
//        //设置标题
//        operLog.setTitle(log.title());
//        //设置操作人类别
//        operLog.setOperatorType(log.operatorType().ordinal());
//        // 是否需要保存request，参数和值
//        if (log.isSaveRequestData())
//        {
//            // 获取参数的信息，传入到数据库中。
//            setRequestValue(joinPoint, operLog);
//        }
//
//    }
//
//    //获取注解
//    public Log getAnnotation(JoinPoint joinPoint){
//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//        if(method != null){
//            return method.getAnnotation(Log.class);
//        }
//        return null;
//    }
//
//    //获取请求的参数，存放到log中
//    private void setRequestValue(JoinPoint joinPoint,SysOperLog operLog){
//        String requestMethod = operLog.getRequestMethod();
//        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))
//        {
//            String params = argsArrayToString(joinPoint.getArgs());
//            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
//        }
//        else
//        {
//            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//            operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
//        }
//    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        String params = "";
        if (paramsArray != null && paramsArray.length > 0) {
            for (int i = 0; i < paramsArray.length; i++) {
                if (!isFilterObject(paramsArray[i])) {
                    Object jsonObj = JSON.toJSON(paramsArray[i]);
                    params += jsonObj.toString() + " ";
                }
            }
        }
        return params.trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    public boolean isFilterObject(final Object o) {
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }


}
