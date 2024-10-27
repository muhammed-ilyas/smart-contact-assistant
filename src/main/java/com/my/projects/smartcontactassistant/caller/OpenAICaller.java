package com.my.projects.smartcontactassistant.caller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.projects.smartcontactassistant.config.ServiceConfig;
import com.my.projects.smartcontactassistant.models.ContactInformation;
import com.my.projects.smartcontactassistant.models.Message;
import com.my.projects.smartcontactassistant.models.MessageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class OpenAICaller {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final ServiceConfig serviceConfig;

    public OpenAICaller(ServiceConfig serviceConfig, ObjectMapper objectMapper) {
        this.serviceConfig = serviceConfig;
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .build();
    }

    public Mono<ContactInformation> extractContactInfo(String address) {

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setModel("gpt-3.5-turbo");
        Message message = getMessage(address);
        messageRequest.setMessages(List.of(message));

        return webClient.post()
                .uri("/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + serviceConfig.getOpenaiApiKey())
                .body(Mono.just(messageRequest), MessageRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(response);
                        String content = jsonNode.path("choices").get(0).path("message").path("content").asText();
                        // Parse the JSON string response into ContactInformation object
                        ContactInformation contactInfo = objectMapper.readValue(content, ContactInformation.class);
                        return Mono.just(contactInfo);
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Failed to parse response", e));
                    }
                });
    }

    private static Message getMessage(String address) {
        Message message = new Message();
        message.setRole("user");
        message.setContent("Please extract the phone number and email address from the following text: \"" + address + "\". " +
                "If you find more than one phone number or email, provide only one, " +
                "If no phone number or email address is present, return empty strings in the JSON response, " +
                "The response should be in the following JSON format: " +
                "{ \"phoneNumber\": \"phone_number\", \"emailAddress\": \"email_address\" }");
        return message;
    }

}
