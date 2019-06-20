package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder;

public class ProductOrderItemDto {
    private ProductDto productDto;
    private Integer quantity;

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductOrderItemDto{" +
                "productDto=" + productDto +
                ", quantity=" + quantity +
                '}';
    }

}
