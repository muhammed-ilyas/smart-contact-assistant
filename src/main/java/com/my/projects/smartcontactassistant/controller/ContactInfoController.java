package com.my.projects.smartcontactassistant.controller;

import com.my.projects.smartcontactassistant.dtos.ContactInfoExtractRequestDTO;
import com.my.projects.smartcontactassistant.models.ContactInformation;
import com.my.projects.smartcontactassistant.service.ContactInfoExtractService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/smart-contact-assistant")
@AllArgsConstructor
@Log4j2
public class ContactInfoController {

    private final ContactInfoExtractService contactInfoExtractService;

    @PostMapping("/extract")
    public Mono<ResponseEntity<ContactInformation>> extractContactInfo(@RequestBody ContactInfoExtractRequestDTO requestDTO) {
        return contactInfoExtractService.extractContactInfo(requestDTO)
                .map(ResponseEntity::ok)
                .doOnError(error -> log.error("Error occurred while extracting contact information: {}", error.getMessage()))
                .onErrorReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
