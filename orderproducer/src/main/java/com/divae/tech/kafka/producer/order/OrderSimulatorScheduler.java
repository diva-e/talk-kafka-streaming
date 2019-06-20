package com.divae.tech.kafka.producer.order;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderSimulatorScheduler {

    private final OrderSimulatorService orderSimulatorService;

    public OrderSimulatorScheduler(OrderSimulatorService orderSimulatorService) {
        this.orderSimulatorService = orderSimulatorService;
    }

    @Scheduled(fixedRateString = "${order.generateRandomOrder.schedule.time}")
    public void generateRandomOrder() {
        orderSimulatorService.generateRandomOrder();
    }
}
