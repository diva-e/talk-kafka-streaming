package com.divae.tech.kafka.producer.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreditCard implements Serializable {

    private static final long serialVersionUID = -4470159916459918018L;

    @JsonProperty("IssuingNetwork")
    private String issuingNetwork;
    @JsonProperty("CardNumber")
    private String cardNumber;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Limit")
    private String limit;

    public String getIssuingNetwork() {
        return issuingNetwork;
    }

    public void setIssuingNetwork(String issuingNetwork) {
        this.issuingNetwork = issuingNetwork;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "issuingNetwork='" + issuingNetwork + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
