package com.example.forum.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
}
