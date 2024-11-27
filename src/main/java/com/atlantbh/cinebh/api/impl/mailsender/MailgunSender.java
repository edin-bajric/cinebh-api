package com.atlantbh.cinebh.api.impl.mailsender;

import com.atlantbh.cinebh.core.api.mailsender.MailSender;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailgunSender implements MailSender {
    private final RestTemplate restTemplate;
    private final String fromEmail;

    @Override
    public String send(List<String> emails, String message, String subject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", fromEmail);

        for (String email : emails) {
            String emailAddress = extractEmailAddressFromJson(email);
            map.add("to", emailAddress);
        }

        map.add("subject", subject);
        map.add("text", message);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity("/messages", request, String.class).getBody();
    }

    private String extractEmailAddressFromJson(String userJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(userJson);
            return jsonNode.get("email").asText();
        } catch (Exception e) {
            return "";
        }
    }
}
