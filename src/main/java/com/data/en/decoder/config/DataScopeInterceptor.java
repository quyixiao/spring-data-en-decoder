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

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.data.en.decoder.utils.OrderUtil;
import com.data.en.decoder.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;


import java.sql.Statement;
import java.text.DateFormat;
import java.util.*;

/**
 * 0
 * @author songfayuan
 * @date 2018/1/19
 * 数据权限插件，guns
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = {Statement.class})})
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 先判断是不是SELECT操作
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        String originalSql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String mapperdId = getMapperId(mappedStatement);
        String sqlCommandTypePre =  mapperdId + " | ";
        if (SqlCommandType.INSERT.equals(mappedStatement.getSqlCommandType())) {
            sqlCommandTypePre = "INSERT= " + mapperdId + " | ";
        } else if (SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
            sqlCommandTypePre = "UPDATE= " + mapperdId + " | ";
        }
        Configuration configuration = mappedStatement.getConfiguration();
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {           //非select语句
                log.info(sqlCommandTypePre + showSql(configuration, boundSql));
            Object result = invocation.proceed();
            return result;
        }
        //查找参数中包含DataScope类型的参数
        DataScope dataScope = findDataScopeObject(parameterObject);
        if (dataScope == null) {
            String sql = getContainsIsDeleteOriginalSql(originalSql);
            log.info(sqlCommandTypePre + showSql(configuration, boundSql));
            metaObject.setValue("delegate.boundSql.sql", sql);
            Object result = invocation.proceed();
            return result;
        } else {
            String scopeName = dataScope.getScopeName();
            List<Integer> deptIds = dataScope.getDeptIds();
            if (StringUtils.isNotBlank(scopeName) && deptIds !=null ) {
                String join = "";
                originalSql = "select * from (" + originalSql + ") temp_data_scope where temp_data_scope." + scopeName + " in (" + join + ")";
                metaObject.setValue("delegate.boundSql.sql", originalSql);
            }
            log.info(sqlCommandTypePre + showSql(configuration, boundSql));
            Object result = invocation.proceed();
            return result;
        }
    }


    // todo quyixiao 所有的sql查询加上is_delete=0,目前没有想到解决办法
    public static String getContainsIsDeleteOriginalSql(String originalSql) {

        return originalSql;
    }


    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "''";
            }
        }
        return value;
    }


    @SuppressWarnings("unused")
    private static Object getParameterValue(String propertyName, Object obj) {

        Object value = null;

        try {
            value = ReflectionUtils.getObjectFieldValue(obj, propertyName);
        } catch (BindingException e2) {
            return null;
        }
        return value;
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
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

    public static String getMapperId(MappedStatement mappedStatement) {
        try {
            String id = mappedStatement.getId();
            if (id.contains(".")) {
                String ids[] = id.split("\\.");
                return ids[ids.length - 2] + "." + ids[ids.length - 1];
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "";
    }

    public static void main(String[] args) {

    }


    /**
     * 查找参数是否包括DataScope对象
     *
     * @param parameterObj 参数列表
     * @return DataScope
     */
    private DataScope findDataScopeObject(Object parameterObj) {
        if (parameterObj instanceof DataScope) {
            return (DataScope) parameterObj;
        } else if (parameterObj instanceof Map) {
            for (Object val : ((Map<?, ?>) parameterObj).values()) {
                if (val instanceof DataScope) {
                    return (DataScope) val;
                }
            }
        }
        return null;
    }


    public static String showSql(Configuration configuration, BoundSql boundSql) {
        try {
            Map<String, String> listMap = new HashMap<>();
            Object parameterObject = boundSql.getParameterObject();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
            if (parameterMappings.size() > 0 && parameterObject != null) {
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    String value = getParameterValue(parameterObject);
                    if (value.contains("?")) {
                        String key = OrderUtil.getUserPoolOrder("rn");
                        listMap.put(key, value);
                        value = key;
                    }
                    sql = sql.replaceFirst("\\?", value);
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    for (ParameterMapping parameterMapping : parameterMappings) {
                        String propertyName = parameterMapping.getProperty();
                        if (metaObject.hasGetter(propertyName)) {
                            Object obj = metaObject.getValue(propertyName);
                            String value = getParameterValue(obj);
                            if (value.contains("?")) {
                                String key = OrderUtil.getUserPoolOrder("rn");
                                listMap.put(key, value);
                                value = key;
                            }
                            sql = sql.replaceFirst("\\?", value);
                        } else if (boundSql.hasAdditionalParameter(propertyName)) {
                            Object obj = boundSql.getAdditionalParameter(propertyName);

                            String value = getParameterValue(obj);
                            if (value.contains("?")) {
                                String key = OrderUtil.getUserPoolOrder("rn");
                                listMap.put(key, value);
                                value = key;
                            }
                            sql = sql.replaceFirst("\\?", value);
                        }
                    }
                }
            }
            if (!listMap.isEmpty()) {
                for (Map.Entry<String, String> m : listMap.entrySet()) {
                    sql = sql.replaceAll(m.getKey(), m.getValue());
                }
            }
            return sql;
        } catch (Exception e) {
            log.error("showSql exception ", e);
        }
        return "";
    }


}
