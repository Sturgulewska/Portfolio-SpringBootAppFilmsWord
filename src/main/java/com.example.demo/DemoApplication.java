package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new SpringApplicationBuilder(DemoApplication.class)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
