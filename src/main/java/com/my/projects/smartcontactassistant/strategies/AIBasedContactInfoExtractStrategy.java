package com.my.projects.smartcontactassistant.strategies;

import com.my.projects.smartcontactassistant.caller.OpenAICaller;
import com.my.projects.smartcontactassistant.models.ContactInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AIBasedContactInfoExtractStrategy implements ContactInfoExtractStrategy {

    private final OpenAICaller openAICaller;

    @Override
    public Mono<ContactInformation> extractContactInfo(String address) {
        // AI based contact info extraction logic
        return openAICaller.extractContactInfo(address);
    }
}
