package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder;

import java.util.ArrayList;
import java.util.List;

public class ProductOrderDto {
    private String idx;
    private List<ProductOrderItemDto> orderItems = new ArrayList<>();
    private Long userIdx;
    private String email;
    private String name;
    private String creditCardNumber;
    private Integer totalAmount = 0;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public List<ProductOrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ProductOrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(Long userIdx) {
        this.userIdx = userIdx;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "ProductOrderDto{" +
                "idx='" + idx + '\'' +
                ", orderItems=" + orderItems +
                ", userIdx=" + userIdx +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }

}
