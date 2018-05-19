package com.equality.springbootdemo2.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 第2个数据库的配置
 * @author E-Quality
 *
 */
@Configuration
@MapperScan(basePackages ={"com.equality.springbootdemo2.mapper.two"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisConfig2 {
	
	public static final String MAPPER_LOCATION = "classpath:mybatis/mapper/two/*Mapper.xml";
	
	@Autowired
    @Qualifier("twoDataSource")
    private DataSource ds2;
	
	@Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2); // 使用主数据源, 连接主库
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(MybatisConfig2.MAPPER_LOCATION);
        //factoryBean.setConfigLocation(configLocation);
        factoryBean.setMapperLocations(resource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
    
    @Bean(name = "twoTransactionManager")
    public DataSourceTransactionManager customTransactionManager() {
        return new DataSourceTransactionManager(ds2);
    }

}
