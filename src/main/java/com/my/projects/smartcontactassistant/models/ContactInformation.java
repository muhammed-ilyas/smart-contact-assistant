package com.my.projects.smartcontactassistant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {
    private String phoneNumber;
    private String emailAddress;
}
