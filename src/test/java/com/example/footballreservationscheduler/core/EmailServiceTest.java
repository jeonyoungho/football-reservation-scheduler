package com.example.footballreservationscheduler.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailSendService emailService;

    @Test
    public void sendMailTest() {
        emailService.sendMail("subject", "body");
    }
}
