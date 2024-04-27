package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Primary
    @Bean("h2DataSource")
    @ConfigurationProperties("spring.datasource.h2")
    public DataSource h2DataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean("h2JdbcTemplate")
    public JdbcTemplate h2JdbcTemplate(@Qualifier("h2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    // 指定h2数据源的初始化sql语句
    @Bean("h2Initializer")
    public DataSourceInitializer h2Initializer(@Qualifier("h2DataSource") DataSource dataSource){
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db/h2/schema.sql"));
        populator.addScript(new ClassPathResource("db/h2/data.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @Bean("sqliteDataSource")
    @ConfigurationProperties("spring.datasource.sqlite")
    public DataSource sqliteDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("sqliteJdbcTemplate")
    public JdbcTemplate sqliteJdbcTemplate(@Qualifier("sqliteDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // 指定sqlite数据源的初始化sql语句
    @Bean("sqliteInitializer")
    public DataSourceInitializer sqliteInitializer(@Qualifier("sqliteDataSource") DataSource dataSource){
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db/sqlite/schema.sql"));
        populator.addScript(new ClassPathResource("db/sqlite/data.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
