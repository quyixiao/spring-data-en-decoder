package com.data.en.decoder.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;


/**
 * 订单号生成工具
 * 枚举相关信息，查看 https://www.showdoc.cc/web/#/page/495445295769339
 */
@Slf4j
public class OrderUtil {

    public static String getUserPoolOrder(String pre) {
        SimpleDateFormat dateformat = new SimpleDateFormat("SSSyyyyMMddHHmmss");
        StringBuffer sb = new StringBuffer(pre);
        return sb.
               append((int) (Math.random() * 1000)).append(dateformat.format(System.currentTimeMillis())).toString();
    }


    public static void addLogNo(String traceId, Long time ) {
        try {
            if(traceId == null){
                traceId = getUserPoolOrder("no");
            }
            if(time == null){
                time = System.currentTimeMillis();
            }
            ch.qos.logback.classic.Logger.inheritableThreadLocalNo.set(traceId);
            ch.qos.logback.classic.Logger.inheritableThreadLocalTime.set(time);
        } catch (Exception e) {
            log.error(" add log error ",e);
        }




    }





    public static String getOrderNo(String pre) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        StringBuffer sb = new StringBuffer(pre);
        return sb.
                append((int) (Math.random() * 1000)).append(dateformat.format(System.currentTimeMillis())).toString();
    }

    public static void removeLogNo() {
        try {
            ch.qos.logback.classic.Logger.inheritableThreadLocalNo.remove();
            ch.qos.logback.classic.Logger.inheritableThreadLocalTime.remove();
        } catch (Exception e) {
            log.error(" remove log error ",e);
        }
    }
    public static void main(String[] args) {
        System.out.println(getOrderNo("ON"));
    }
}
