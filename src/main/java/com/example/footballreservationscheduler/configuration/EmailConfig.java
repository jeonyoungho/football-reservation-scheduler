package com.example.footballreservationscheduler.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by jyh1004 on 2023-04-19
 */

@Configuration
public class EmailConfig {

    @Value("${notify.smtp.mail.username}")
    private String mail;

    @Value("${notify.smtp.mail.password}")
    private String password;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost("smtp.gmail.com");
        javaMailSenderImpl.setPort(587);
        javaMailSenderImpl.setUsername(mail);
        javaMailSenderImpl.setPassword(password);

        Properties props = javaMailSenderImpl.getJavaMailProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.ssl.trust", "*");

        return javaMailSenderImpl;
    }
}
