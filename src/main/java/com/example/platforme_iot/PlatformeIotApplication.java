package com.example.platforme_iot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.example.platforme_iot")
public class PlatformeIotApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformeIotApplication.class, args);
    }

}
