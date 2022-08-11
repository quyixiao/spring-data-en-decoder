package com.data.en.decoder.config;


import com.lz.mybatis.plugin.config.ResolverBeanPostProcessor;
import com.lz.mybatis.plugins.interceptor.EncryptTableConfig;
import com.lz.mybatis.plugins.interceptor.utils.PSpringContextUtils;
import com.lz.mybatis.plugins.interceptor.utils.t.PPTuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Slf4j
@Configuration
public class MybatisPluginConfig {


    //初始化变量
    @PostConstruct
    public void postConstruct() {
        log.info("开始初始化表数据");
        EncryptTableConfig.tableConfig.put("lt_user_phone", new PPTuple(Arrays.asList(new String[]{"real_name_en", "user_name_en", "id_number_en"}),
                Arrays.asList(new String[]{})));
    }


    @Bean
    public ResolverBeanPostProcessor resolverBeanPostProcessor() {

        return new ResolverBeanPostProcessor(new MyBatisBaomidouServiceImpl());
    }


    @Bean
    public PSpringContextUtils pSpringContextUtils() {
        return new PSpringContextUtils();
    }

}
