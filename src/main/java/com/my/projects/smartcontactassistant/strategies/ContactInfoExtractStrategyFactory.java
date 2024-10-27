package com.my.projects.smartcontactassistant.strategies;

import com.my.projects.smartcontactassistant.caller.OpenAICaller;
import com.my.projects.smartcontactassistant.enums.ContactInfoExtractor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContactInfoExtractStrategyFactory {

    private final HumanBasedContactInfoExtractStrategy humanBasedContactInfoExtractStrategy;
    private final AIBasedContactInfoExtractStrategy aiBasedContactInfoExtractStrategy;

    public ContactInfoExtractStrategy getStrategy(ContactInfoExtractor extractor) {
        return switch (extractor) {
            case HUMAN -> humanBasedContactInfoExtractStrategy;
            case BOT -> aiBasedContactInfoExtractStrategy;
        };
    }
}
