package com.atlantbh.cinebh.core.api.mailsender;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    void send(String email, String message, String subject);
}
