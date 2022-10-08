/*
 *    Copyright (c) 2018-2025, songfayuan All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the 霖梓控股 developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: songfayuan (1414798079@qq.com)
 */

package com.data.en.decoder.config;

import com.data.en.decoder.aop.ServiceAop;
import com.lz.mybatis.plugins.interceptor.baomidou.SqlParserHandler;
import com.lz.mybatis.plugins.interceptor.baomidou.toolkit.PluginUtils;
import com.lz.mybatis.plugins.interceptor.utils.PSqlParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author songfayuan
 * @date 2018/1/19
 * 数据权限插件，guns
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "update", args = {Statement.class})})
public class SQLInsertLogInterceptr extends SqlParserHandler implements Interceptor {


    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(SQLInsertLogInterceptr.class);


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 先判断是不是SELECT操作
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String mapperdId = PSqlParseUtil.getMapperId(mappedStatement);
        Configuration configuration = mappedStatement.getConfiguration();
        Object result = invocation.proceed();
        Object parameterObject = boundSql.getParameterObject();
        if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
            Throwable throwable = new Throwable();
            String sqlCommandTypePre = mapperdId + " ";
            String peek0 = ServiceAop.peek();
            if (StringUtils.isNotBlank(peek0)) {
                StackTraceElement[] stackTraceElements = throwable.getStackTrace();
                String classInfos[] = peek0.split(":");
                int i = 0;
                for (StackTraceElement stackTraceElement : stackTraceElements) {
                    i++;
                    if (stackTraceElement.getClassName().equals(classInfos[0]) && stackTraceElement.getMethodName().equals(classInfos[1])) {
                        String className = stackTraceElement.getClassName();
                        int lastIndexOf = className.lastIndexOf(".");
                        className = className.substring(lastIndexOf + 1);
                        sqlCommandTypePre = className + ":" + stackTraceElement.getLineNumber() + ":" + mapperdId + " ";
                    }
                    if (i > 100) {
                        break;
                    }
                }
            }
            sqlCommandTypePre = "" + sqlCommandTypePre + " ";
            String sql = DataScopeInterceptor.showSql(configuration, boundSql);
            try {
                MetaObject insertObject = SystemMetaObject.forObject(parameterObject);
                sql = sql + " | insert_id=" + insertObject.getValue("id");
            } catch (Exception e) {
                log.error("SQLInsertInterceptor抛出异常", e);
            }
            logger.info(sqlCommandTypePre + sql);
        }
        return result;
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            //使用MyBatis提供的Plugin类生成代理对象
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * @param properties mybatis获取插件的属性，我们在MyBatis配置文件里配置的
     */
    @Override
    public void setProperties(Properties properties) {

    }


}
