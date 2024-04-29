package com.example.demo.aspect;

import com.example.demo.annotation.Log;
import com.example.demo.bean.SysLog;
import com.example.demo.mapper.SysLogMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Date;


@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(com.example.demo.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行耗时（毫秒）
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();

        Log annotation = method.getAnnotation(Log.class);
        // 注解的值
        if (annotation != null) sysLog.setOperation(annotation.value());
        // 方法名称
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 参数值
        Object[] args = point.getArgs();
        // 参数名称
        StandardReflectionParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        if (args != null && parameterNames != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                sb.append(" ").append(parameterNames[i]).append(": ").append(args[i]);
            }
            sysLog.setParams(sb.toString());
        }
        // 获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 设置IP
        sysLog.setIp(getIpAddress(request));
        // 模拟一个用户
        sysLog.setUsername("mrbird");
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Date());
        sysLogMapper.saveSysLog(sysLog);
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;

        ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress != null && !ipAddress.equalsIgnoreCase("unknown")) {
            return ipAddress;
        }

        ipAddress = request.getHeader("Proxy-Client-IP");
        if (ipAddress != null && !ipAddress.equalsIgnoreCase("unknown")) {
            return ipAddress;
        }

        ipAddress = request.getHeader("WL-Proxy-Client-IP");
        if (ipAddress != null && !ipAddress.equalsIgnoreCase("unknown")) {
            return ipAddress;
        }

        ipAddress = request.getHeader("HTTP-Client-IP");
        if (ipAddress != null && !ipAddress.equalsIgnoreCase("unknown")) {
            return ipAddress;
        }

        ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress != null && !ipAddress.equalsIgnoreCase("unknown")) {
            return ipAddress;
        }

        ipAddress = request.getRemoteAddr();
        return ipAddress;
    }
}
