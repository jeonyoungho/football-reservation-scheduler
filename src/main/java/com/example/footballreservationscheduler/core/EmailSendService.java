package com.example.footballreservationscheduler.core;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by jyh1004 on 2023-04-19
 */

@RequiredArgsConstructor
@Service
public class EmailSendService {

    @Value("${notify.receiver-email}")
    private String receiverEmail;

    private final JavaMailSender mailSender;

    public void sendMail(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
