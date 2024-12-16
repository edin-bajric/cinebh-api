package com.atlantbh.cinebh.api.impl.mailsender;

import com.atlantbh.cinebh.core.api.mailsender.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MailgunSender implements MailSender {
    private final RestTemplate restTemplate;
    private final String fromEmail;

    @Override
    public void send(String email, String message, String subject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", fromEmail);

        map.add("to", email);
        map.add("subject", subject);
        map.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        restTemplate.postForEntity("/messages", request, String.class).getBody();
    }
}
