package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
// 扫描sqlitemapper包下的mapper接口，并将其注册到sqliteSqlSessionFactory
@MapperScan(basePackages = "com.example.demo.sqlitemapper", sqlSessionFactoryRef = "sqliteSqlSessionFactory")
public class SqliteDatasourceConfig {
    @Bean("sqliteDataSource")
    // 使用spring.datasource.sqlite为前缀的配置项构建sqlite数据源
    @ConfigurationProperties("spring.datasource.sqlite")
    public DataSource sqliteDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("sqliteTransactionManager")
    public DataSourceTransactionManager sqliteTransactionManager() {
        return new DataSourceTransactionManager(sqliteDataSource());
    }

    // 使用sqliteDataSource构建SqlSessionFactory
    @Bean("sqliteSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("sqliteDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }

    // 指定sqlite数据源的初始化sql语句
    @Bean("sqliteInitializer")
    public DataSourceInitializer h2Initializer(@Qualifier("sqliteDataSource") DataSource sqliteDatasource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(sqliteDatasource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db/sqlite/schema.sql"));
        populator.addScript(new ClassPathResource("db/sqlite/data.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
