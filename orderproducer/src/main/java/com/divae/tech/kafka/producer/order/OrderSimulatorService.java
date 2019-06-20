package com.divae.tech.kafka.producer.order;

import com.divae.tech.kafka.producer.customer.CustomerPersistenceService;
import com.divae.tech.kafka.producer.model.CustomerDto;
import com.divae.tech.kafka.producer.model.ProductDto;
import com.divae.tech.kafka.producer.model.ProductOrderDto;
import com.divae.tech.kafka.producer.model.ProductOrderItemDto;
import com.divae.tech.kafka.producer.products.ProductPersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderSimulatorService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderSimulatorService.class);

    private final CustomerPersistenceService customerPersistenceService;
    private final ProductPersistenceService productPersistenceService;
    private final KafkaOrderService kafkaOrderService;

    public OrderSimulatorService(CustomerPersistenceService customerPersistenceService,
                                 ProductPersistenceService productPersistenceService,
                                 KafkaOrderService kafkaOrderService) {
        this.customerPersistenceService = customerPersistenceService;
        this.productPersistenceService = productPersistenceService;
        this.kafkaOrderService = kafkaOrderService;
    }


    public void generateRandomOrder() {


        final CustomerDto customer = getRandomCustomer();
        final ProductOrderDto orderDto = createOrder(customer);

        kafkaOrderService.sendOrderToKafka(orderDto);
    }

    private ProductOrderDto createOrder(CustomerDto customer) {
        final ProductOrderDto orderDto = new ProductOrderDto();
        orderDto.setIdx(UUID.randomUUID().toString());
        orderDto.setUserIdx(customer.getIdx());
        orderDto.setCreditCardNumber(customer.getCreditCardNumber());
        orderDto.setEmail(customer.getEmail());
        orderDto.setName(customer.getName());

        int orderPositions = (int) ((Math.random()) * 5 + 1);
        for (int i = 1; i <= orderPositions; i++) {
            final ProductOrderItemDto orderItemDto = new ProductOrderItemDto();
            final ProductDto product = getRandomProduct();
            orderItemDto.setProductDto(product);
            final Integer randomQuantity = getRandomQuantity();
            orderItemDto.setQuantity(randomQuantity);
            orderDto.getOrderItems().add(orderItemDto);
            final Integer currentTotalAmount = orderDto.getTotalAmount();
            orderDto.setTotalAmount(currentTotalAmount + (product.getPrice() * randomQuantity));
        }

        return orderDto;

    }

    private ProductDto getRandomProduct() {
        final List<ProductDto> products = new ArrayList<>(productPersistenceService.getAllProducts());
        int randomPosition = (int) ((Math.random()) * products.size());

        return products.get(randomPosition);
    }

    private CustomerDto getRandomCustomer() {
        final List<CustomerDto> customers = new ArrayList<>(customerPersistenceService.getAllCustomers());

        int randomPosition = (int) ((Math.random()) * customers.size());

        return customers.get(randomPosition);
    }

    private Integer getRandomQuantity() {
        return (int) ((Math.random()) * 10 + 1);
    }
}
