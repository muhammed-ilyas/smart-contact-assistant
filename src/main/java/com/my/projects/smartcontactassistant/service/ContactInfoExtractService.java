package com.my.projects.smartcontactassistant.service;

import com.my.projects.smartcontactassistant.dtos.ContactInfoExtractRequestDTO;
import com.my.projects.smartcontactassistant.enums.ContactInfoExtractor;
import com.my.projects.smartcontactassistant.models.ContactInformation;
import com.my.projects.smartcontactassistant.strategies.ContactInfoExtractStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ContactInfoExtractService {

    private final ContactInfoExtractStrategyFactory contactInfoExtractStrategyFactory;

    public Mono<ContactInformation> extractContactInfo(ContactInfoExtractRequestDTO requestDTO) {
        return contactInfoExtractStrategyFactory
                .getStrategy(ContactInfoExtractor.valueOf(requestDTO.getExtractor()))
                .extractContactInfo(requestDTO.getAddress());
    }

}
