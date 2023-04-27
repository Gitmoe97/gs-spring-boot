package com.example.springboot;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class DataSourceConfig {
    
@Bean public static DataSource source() {
    
    DataSourceBuilder<?> dsb = DataSourceBuilder.create();
    dsb.driverClassName("com.mysql.jdbc.Driver");

    dsb.url("jdbc:mysql://localhost:3306/mydb");
    dsb.username("root");
    dsb.password("Hantash97");

    return dsb.build();
}

}
