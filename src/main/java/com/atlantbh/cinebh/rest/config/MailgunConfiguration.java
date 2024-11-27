package com.atlantbh.cinebh.rest.config;

import com.atlantbh.cinebh.api.impl.mailsender.MailgunSender;
import com.atlantbh.cinebh.core.api.mailsender.MailSender;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@Getter
public class MailgunConfiguration {
    @Value("${email.mailgun.username}")
    private String username;
    @Value("${email.mailgun.password}")
    private String password;
    @Value("${email.mailgun.domain}")
    private String domain;
    @Value("${email.mailgun.from-email}")
    private String fromEmail;

    @Bean
    public String fromEmail() {
        return fromEmail;
    }

    @Bean
    public MailSender mailgunMailSender(RestTemplate restTemplate, String fromEmail) {
        return new MailgunSender(restTemplate, fromEmail);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.uriTemplateHandler(new DefaultUriBuilderFactory(domain))
                .basicAuthentication(username, password)
                .build();
    }
}