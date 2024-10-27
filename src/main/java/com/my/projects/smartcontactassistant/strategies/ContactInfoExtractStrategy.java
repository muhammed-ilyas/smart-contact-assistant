package com.my.projects.smartcontactassistant.strategies;

import com.my.projects.smartcontactassistant.models.ContactInformation;
import reactor.core.publisher.Mono;

public interface ContactInfoExtractStrategy {
    Mono<ContactInformation> extractContactInfo(String address);
}
