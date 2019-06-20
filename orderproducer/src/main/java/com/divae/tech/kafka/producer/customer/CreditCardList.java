package com.divae.tech.kafka.producer.customer;

import java.util.List;
import java.util.Map;

public class CreditCardList {

    private List<Map<String, Map<String, String>>> creditCards;

    public List<Map<String, Map<String, String>>> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<Map<String, Map<String, String>>> creditCards) {
        this.creditCards = creditCards;
    }

}
