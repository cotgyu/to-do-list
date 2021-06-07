package com.toy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ModuleWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleWebApplication.class, args);
    }

}
