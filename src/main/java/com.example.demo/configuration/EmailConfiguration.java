package com.example.demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class EmailConfiguration {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.login}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.sender}")
    private String emailSender;
}
