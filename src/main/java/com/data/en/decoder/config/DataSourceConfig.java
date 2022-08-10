package com.data.en.decoder.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.lz.mybatis.plugins.interceptor.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = { "com.data.en.decoder.dao" }, sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class DataSourceConfig implements EnvironmentAware {
	@Autowired
	private Environment env;

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}


	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DataSource initDataSource() {
		return new DruidDataSource();
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(initDataSource());
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory initSqlSessionFactory( PaginationInterceptor paginationInterceptor) throws Exception {
		MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
		sessionFactory.setDataSource(initDataSource());
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources(env.getProperty("mybatis-plus.mapper-locations")));
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		Interceptor[] interceptor = {paginationInterceptor,new DataScopeInterceptor(),
				new DataQueryEncryptScopeInterceptor(),
				new DataUpdateEncryptScopeInterceptor(),
				new RestoreQueryDataScopeInterceptor(),
				new RestoreUpdateDataScopeInterceptor(),
				new QueryDecryptScopeInterceptor(),
				new MapF2FInterceptor()};


		//Interceptor[] interceptor = {paginationInterceptor,new DataScopeInterceptor()};


		sessionFactory.setPlugins(interceptor);
		return sessionFactory.getObject();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		// 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
		// paginationInterceptor.setOverflow(false);
		// 设置最大单页限制数量，默认 500 条，-1 不受限制
		// paginationInterceptor.setLimit(500);
		// 开启 count 的 join 优化,只针对部分 left join
		paginationInterceptor.setSqlParser(new JsqlParserCountOptimize());
		return paginationInterceptor;
	}

	@Bean(name = "transactionTemplate")
	public TransactionTemplate transactionTemplate(@Qualifier("transactionManager") DataSourceTransactionManager transactionManager) {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);
		transactionTemplate.setIsolationLevelName("ISOLATION_READ_UNCOMMITTED");
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);

		return transactionTemplate;
	}
}
