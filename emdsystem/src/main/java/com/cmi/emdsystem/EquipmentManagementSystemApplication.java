package com.cmi.emdsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.cmi.emdsystem.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EquipmentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentManagementSystemApplication.class, args);
    }

    /* For Cross Origin CORS Policy */
}
