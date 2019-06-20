package com.divae.tech.kafka.producer.customer;

import com.divae.tech.kafka.producer.model.CustomerDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CustomerGenerationService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerGenerationService.class);


    private final CustomerPersistenceService customerPersistenceService;
    private final String masterCardFile;


    public CustomerGenerationService(CustomerPersistenceService customerPersistenceService,
                                     @Value("${mastercard.file.src}") String masterCardFile) {
        this.customerPersistenceService = customerPersistenceService;
        this.masterCardFile = masterCardFile;
        importGeneratedUsers();
    }

    public void importGeneratedUsers() {


        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            final CreditCardList creditCardList = objectMapper.readValue(new File(masterCardFile), CreditCardList.class);
            final List<Map<String, Map<String, String>>> creditCardListWrapper = creditCardList.getCreditCards();
            int i = 0;
            for (final Map<String, Map<String, String>> creditCards : creditCardListWrapper) {
                i++;
                final Map<String, String> creditCard = creditCards.get("CreditCard");
                final CustomerDto dto = createCustomerFromCreditCard(creditCard);
                dto.setIdx(Long.valueOf(i));
                customerPersistenceService.addCustomer(dto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private CustomerDto createCustomerFromCreditCard(Map<String, String> creditCard) {
        final CustomerDto dto = new CustomerDto();
        dto.setName(creditCard.get("Name"));
        final String generatedEmail = createEmailValidName(creditCard.get("Name")) + "@foobar.de";
        dto.setEmail(generatedEmail);
        dto.setPassword("test12");
        dto.setCreditCardNumber(creditCard.get("CardNumber"));

        return dto;

    }


    private String createEmailValidName(String name) {
        String retVal = name.toLowerCase();
        retVal = retVal.replace(" ", "");
        retVal = retVal.replace("ü", "ue");
        retVal = retVal.replace("ä", "ae");
        retVal = retVal.replace("ö", "oe");
        retVal = retVal.replace("ß", "ss");

        return retVal;
    }


}
