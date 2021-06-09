package com.example.demo.service;

import com.example.demo.configuration.EmailConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final EmailConfiguration emailConfiguration;

    public EmailService(EmailConfiguration emailConfiguration) {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(emailConfiguration.getHost());
        mailSenderImpl.setPassword(emailConfiguration.getPassword());
        mailSenderImpl.setPort(emailConfiguration.getPort());
        mailSenderImpl.setUsername(emailConfiguration.getUsername());

        this.javaMailSender = mailSenderImpl;
        this.emailConfiguration = emailConfiguration;
    }

    public void sendEmail(String email, String tittle, String body) throws MessagingException {
        MimeMessage message = createMessage(email, tittle, body);
        javaMailSender.send(message);
    }

    private MimeMessage createMessage(String email, String title, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setSubject(title);
        messageHelper.setText(body);
        messageHelper.setTo(email);
        messageHelper.setFrom(emailConfiguration.getEmailSender());
        return message;
    }
}
