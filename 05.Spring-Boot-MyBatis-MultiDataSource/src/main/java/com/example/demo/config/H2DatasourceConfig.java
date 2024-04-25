package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
// 扫描h2mapper包下的mapper接口，并将其注册到h2SqlSessionFactory
@MapperScan(basePackages = "com.example.demo.h2mapper", sqlSessionFactoryRef = "h2SqlSessionFactory")
public class H2DatasourceConfig {

    @Primary
    @Bean("h2DataSource")
    // 使用spring.datasource.h2为前缀的配置项构建h2数据源
    @ConfigurationProperties("spring.datasource.h2")
    public DataSource h2DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("h2TransactionManager")
    public DataSourceTransactionManager h2TransactionManager() {
        return new DataSourceTransactionManager(h2DataSource());
    }

    @Primary
    // 使用h2DataSource构建SqlSessionFactory
    @Bean("h2SqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("h2DataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    @Primary
    // 指定h2数据源的初始化sql语句
    @Bean("h2Initializer")
    public DataSourceInitializer h2Initializer(@Qualifier("h2DataSource") DataSource h2Datasource){
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(h2Datasource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db/h2/schema.sql"));
        populator.addScript(new ClassPathResource("db/h2/data.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
