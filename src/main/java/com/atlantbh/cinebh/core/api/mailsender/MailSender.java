package com.atlantbh.cinebh.core.api.mailsender;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MailSender {
    String send(List<String> emails, String message, String subject);
}
