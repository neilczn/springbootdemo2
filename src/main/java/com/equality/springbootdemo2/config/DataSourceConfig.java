package com.equality.springbootdemo2.config;






import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 多数据源配置
 * @author E-Quality
 *
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
	@Bean(name = "dataSource")
    @Qualifier("dataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource oneDataSource() {
		//return DataSourceBuilder.create().build();//spring boot 2.0 多数据源时 ，如果此种创建， application.properties 中 使用 jdbc-url
        return new DruidDataSource();//使用Druid
    }
	
	@Bean(name = "jdbcTemplate")
    public JdbcTemplate oneJdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
	
	@Bean(name = "twoDataSource")
    @Qualifier("twoDataSource")
    @ConfigurationProperties(prefix="spring.datasource.two")
    public DataSource twoDataSource() {
		//return DataSourceBuilder.create().build();
		return new DruidDataSource();
    }
	
	@Bean(name = "twoJdbcTemplate")
    public JdbcTemplate twoJdbcTemplate(@Qualifier("twoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
