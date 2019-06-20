package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderStreamingStarter implements CommandLineRunner {

    private final ProductOrderStreamingTopology productOrderStreamingTopology;

    public ProductOrderStreamingStarter(ProductOrderStreamingTopology productOrderStreamingTopology) {
        this.productOrderStreamingTopology = productOrderStreamingTopology;
    }

    @Override
    public void run(String... args) throws Exception {
        productOrderStreamingTopology.streamIncommingProductOrders();
    }
}
