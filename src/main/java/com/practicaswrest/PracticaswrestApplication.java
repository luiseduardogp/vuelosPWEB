package com.practicaswrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticaswrestApplication {

    public static void main(String[] args) {
        System.setProperty("server.port", "8096");
        SpringApplication.run(PracticaswrestApplication.class, args);
    }

}
