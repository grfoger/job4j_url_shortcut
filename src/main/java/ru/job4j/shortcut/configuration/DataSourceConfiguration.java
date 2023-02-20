package ru.job4j.shortcut.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@AutoConfiguration
public class DataSourceConfiguration {
    @Bean
    public DataSource ds() {
        BasicDataSource ds = new BasicDataSource();
        return ds;
    }
}
