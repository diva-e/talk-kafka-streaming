package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.billing;

public class BillingDto {

    private Integer amount;
    private String creditCardNumber;
    private String name;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillingDto{" +
                "amount=" + amount +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
