package com.data.en.decoder.config;


import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.baomidou.mybatisplus.core.override.PageMapperMethod;
import com.baomidou.mybatisplus.core.override.PageMapperProxyFactory;
import com.lz.mybatis.plugin.service.MyBatisBaomidouService;
import com.lz.mybatis.plugin.utils.SqlParseUtils;
import com.lz.mybatis.plugin.utils.t.PluginTuple;
import com.lz.mybatis.plugin.utils.t.Tuple2;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ParamNameResolver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyBatisBaomidouServiceImpl implements MyBatisBaomidouService {

    @Override
    public void init(List<PluginTuple> pluginTuples, Configuration configuration, Class type) {
        try {
            MybatisMapperRegistry mapperRegistry = (MybatisMapperRegistry) configuration.getMapperRegistry();
            Map<Class<?>, PageMapperProxyFactory<?>> knownMappers = SqlParseUtils.getFieldValue(mapperRegistry, "knownMappers");
            PageMapperProxyFactory mapperProxyFactory = knownMappers.get(type);
            Map<Method, PageMapperMethod> methodCache = mapperProxyFactory.getMethodCache();
            for (PluginTuple pluginTuple : pluginTuples) {
                Tuple2<Boolean, Method> data = pluginTuple.getData();
                Method method = data.getSecond();
                PageMapperMethod mapperMethod = methodCache.get(method);
                if (mapperMethod == null) {
                    if (mapperProxyFactory.getMapperInterface() != null) {
                        mapperMethod = new PageMapperMethod(mapperProxyFactory.getMapperInterface(), method, configuration);
                    } else {
                        mapperMethod = new PageMapperMethod(type, method, configuration);
                    }
                    PageMapperMethod.MethodSignature methodSignature = SqlParseUtils.getFieldValue(mapperMethod, "method");
                    ParamNameResolver paramNameResolver = SqlParseUtils.getFieldValue(methodSignature, "paramNameResolver");
                    SqlParseUtils.setFieldValue(paramNameResolver, "hasParamAnnotation", data.getFirst());
                    methodCache.put(method, mapperMethod);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void info(String info) {
        //log.info(info);
    }
}
 