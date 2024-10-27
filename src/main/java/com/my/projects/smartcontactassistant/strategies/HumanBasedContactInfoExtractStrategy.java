package com.my.projects.smartcontactassistant.strategies;

import com.my.projects.smartcontactassistant.models.ContactInformation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Component
public class HumanBasedContactInfoExtractStrategy implements ContactInfoExtractStrategy {
    @Override
    public Mono<ContactInformation> extractContactInfo(String address) {
        // Human based contact info extraction logic
        List<String> list = List.of(address.split(" "));
        String email = list.stream()
                .map(e -> e.replace(",", ""))
                .filter(e -> e.contains("@"))
                .findFirst().orElse("");
        String phone = list.stream()
                .map(e -> e.replace(",", ""))
                .filter(e -> e.matches("^\\d+$"))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        ContactInformation contactInformation = ContactInformation.builder()
                .emailAddress(email)
                .phoneNumber(phone)
                .build();
        return Mono.just(contactInformation);
    }
}
