package com.divae.tech.kafka.producer.products;

import com.divae.tech.kafka.producer.customer.CustomerGenerationService;
import com.divae.tech.kafka.producer.model.ProductDto;
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
public class ProductGenerationService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerGenerationService.class);


    private final ProductPersistenceService productPersistenceService;
    private final String productFile;


    public ProductGenerationService(ProductPersistenceService productPersistenceService,
                                    @Value("${product.file.src}") String productFile) {
        this.productPersistenceService = productPersistenceService;
        this.productFile = productFile;
        initProducts();
    }

    private void initProducts() {

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            final ProductList productList = objectMapper.readValue(new File(productFile), ProductList.class);
            final List<Map<String, String>> products = productList.getProducts();
            for (final Map<String, String> productDetailMap : products) {
                final ProductDto dto = new ProductDto();
                dto.setIdx(productDetailMap.get("idx"));
                dto.setName(productDetailMap.get("name"));
                dto.setCategory(productDetailMap.get("category"));
                dto.setPrice(Integer.parseInt(productDetailMap.get("price")));
                productPersistenceService.addProduct(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
