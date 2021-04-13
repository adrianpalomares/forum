package com.example.forum.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationEmail(String toEmailAddress) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Forum<apalom22@gmail.com>");
        simpleMailMessage.setTo(toEmailAddress);
        simpleMailMessage.setSubject("Welcome to Forum!");
        simpleMailMessage.setText("This is the registration email.");
        javaMailSender.send(simpleMailMessage);
    }
}
