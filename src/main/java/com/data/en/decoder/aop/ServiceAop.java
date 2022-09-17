package com.data.en.decoder.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志aop
 * Created by wutao on 2018/10/12.
 */
@Aspect
@Component
@Order(2)
@Slf4j
public class ServiceAop {


    public final static ThreadLocal<ArrayStack> arrayStackThreadLocal = new ThreadLocal();


    @Pointcut(value = "( " +
            "   execution(* com.data.en.decoder..*.*Impl.*(..))  " +
            "   || execution(* com.data.en.decoder..*.*Controller.*(..))  " +
            "   )")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        String no = ch.qos.logback.classic.Logger.inheritableThreadLocalNo.get();
        if (StringUtils.isBlank(no)) {
            try {
                return point.proceed();
            } catch (Exception e) {
                throw e;
            }
        }
        Object result = null;
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String allClassName = method.getDeclaringClass().getName(); // 全类名
        Throwable throwable = new Throwable();
        String lineNumber = getLineNumber(allClassName, method.getName(), throwable);
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        String key = lineNumber + "=>" + methodName + ":" + className;
        String oldNo = "";
        try {
            oldNo = ch.qos.logback.classic.Logger.inheritableThreadLocalNo.get();
            ch.qos.logback.classic.Logger.inheritableThreadLocalNo.set(oldNo + key);
            result = point.proceed();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            ch.qos.logback.classic.Logger.inheritableThreadLocalNo.set(oldNo);
            pop();
            if(className.endsWith("Controller")){
                arrayStackThreadLocal.remove();
            }
        }
    }

    public static String getLineNumber(String className, String methodName, Throwable throwable ) {
        String classInfo = className +":"+ methodName;
        try {
            if(className.endsWith("Controller")){
                return "";
            }

            String peek = peek();
            if(StringUtils.isBlank(peek)){
                return "";
            }
            StackTraceElement[] elements =throwable.getStackTrace();
            for (StackTraceElement stackTraceElement : elements) {
                String c = stackTraceElement.getClassName();
                String mName = stackTraceElement.getMethodName();
                if(peek.equals(c +":"+ mName)){
                    return ":"+stackTraceElement.getLineNumber();
                }
            }
        } catch (Exception e) {
            log.error("解析类信息异常", e);
        } finally {
            push(classInfo);
        }
        return "";
    }


    public static void  push(String classInfo ){
        ArrayStack arrayStack = arrayStackThreadLocal.get();
        if(arrayStack == null){
            arrayStack = new ArrayStack();
        }
        arrayStack.push( classInfo);
        arrayStackThreadLocal.set(arrayStack);
    }

    public static String  peek(){
        ArrayStack arrayStack = arrayStackThreadLocal.get();
        if(arrayStack == null){
            return null;
        }
        if(arrayStack.size() > 0 ){
            return (String) arrayStack.peek();
        }
        return null;
    }

    public static String  pop(){
        ArrayStack arrayStack = arrayStackThreadLocal.get();
        if(arrayStack == null){
            return null;
        }
        if(arrayStack.size() > 0 ){
            return (String) arrayStack.pop();
        }
        return null;
    }

}
