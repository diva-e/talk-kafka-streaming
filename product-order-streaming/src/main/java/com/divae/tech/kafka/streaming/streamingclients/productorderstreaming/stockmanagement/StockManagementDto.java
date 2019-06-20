package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.stockmanagement;

import java.io.Serializable;

public class StockManagementDto implements Serializable {
    private static final long serialVersionUID = 4036335133291701649L;

    private String productIdx;
    private Integer numberOfItems;

    public String getProductIdx() {
        return productIdx;
    }

    public void setProductIdx(String productIdx) {
        this.productIdx = productIdx;
    }

    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    @Override
    public String toString() {
        return "StockManagementDto{" +
                "productIdx='" + productIdx + '\'' +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}
