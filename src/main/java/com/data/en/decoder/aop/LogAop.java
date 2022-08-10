package com.data.en.decoder.aop;

import com.alibaba.fastjson.JSON;
import com.data.en.decoder.utils.OrderUtil;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志aop
 * Created by wutao on 2018/10/12.
 */
@Aspect
@Component
@Order(1)
public class LogAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String BLANK_SPACE = "\t";


    @Pointcut(value = "(@within(org.springframework.web.bind.annotation.RestController) " +
            "   || @within(org.springframework.stereotype.Controller))")
    public void pointcut() {
    }


    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        StringBuffer sb = new StringBuffer();
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String traceId = attributes.getRequest().getHeader("traceId");
            if (StringUtils.isBlank(traceId)) {
                traceId = OrderUtil.getOrderNo("on");
            }
            ch.qos.logback.classic.Logger.inheritableThreadLocalNo.set(traceId);
            ch.qos.logback.classic.Logger.inheritableThreadLocalTime.set(System.currentTimeMillis());
            sb.append(recordRequestLog(point.getArgs()).toString());
            result = point.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            sb.append("resD=").append(JSON.toJSONString(result));
            logger.info(sb.toString());
            OrderUtil.removeLogNo();
        }
        return result;
    }

    private StringBuffer recordRequestLog(Object[] argArrs) {
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        sb.append("【app】").append(BLANK_SPACE);
        String uri = "";
        String mediaType = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        mediaType = request.getContentType();
        try {
            String args = null;
            uri = request.getRequestURI();

            List<Object> list = new ArrayList<>();
            if (argArrs != null && argArrs.length > 0) {
                for (Object arg1 : argArrs) {
                    if (arg1 instanceof HttpServletResponse) {
                        continue;
                    } else if (arg1 instanceof HttpServletRequest) {
                        continue;
                    } else if (arg1 instanceof MultipartFile) {
                        continue;
                    } else if (arg1 instanceof MultipartFile[]) {
                        continue;
                    } else if (arg1 instanceof ResponseFacade) {
                        continue;
                    } else if (arg1 instanceof org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper) {
                        continue;
                    } else {
                        list.add(arg1);
                    }
                }
            }
            args = JSON.toJSONString(list);
            sb.append(request.getRequestURI()).append(BLANK_SPACE)
                    .append("rmtIP=").append("127.0.0.1").append(BLANK_SPACE);
            sb.append("userName=").append("184xxx").append(BLANK_SPACE);
            sb.append("ipAddr=").append("ip地址").append(BLANK_SPACE);
            sb.append("appVersion=").append("app版本").append(BLANK_SPACE);
            sb.append("devOS=").append("操作类型安卓IOS").append(BLANK_SPACE);
            sb.append("devOSVersion=").append("操作系统版本").append(BLANK_SPACE);
            sb.append("wifiName=").append("wifi名称").append(BLANK_SPACE);
            sb.append("reqD=").append(args).append(BLANK_SPACE);
        } catch (Exception e) {
            logger.error("url " + request.getRequestURL() + " , uri " + uri + " ,mediaType " + mediaType + ", 打印请求日志错误", e);
        }
        return sb;
    }


}
