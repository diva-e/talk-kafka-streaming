package com.divae.tech.kafka.producer.products;

import com.divae.tech.kafka.producer.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPersistenceService {

    private final List<ProductDto> products = new ArrayList<>();

    public List<ProductDto> getAllProducts() {
        return products;
    }

    public void addProduct(final ProductDto dto) {
        products.add(dto);
    }

}
