package com.divae.tech.kafka.producer.order;

import com.divae.tech.kafka.producer.model.ProductOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaOrderService.class);


    private final KafkaTemplate<String, ProductOrderDto> kafkaProductOrderDtoTemplate;
    private final String topicNameOrders;

    public KafkaOrderService(KafkaTemplate<String, ProductOrderDto> kafkaProductOrderDtoTemplate,
                             @Value("${kafka.orders.topicName}") String topicNameOrders) {
        this.kafkaProductOrderDtoTemplate = kafkaProductOrderDtoTemplate;
        this.topicNameOrders = topicNameOrders;
    }


    public void sendOrderToKafka(final ProductOrderDto productOrderDto) {
        ListenableFuture<SendResult<String, ProductOrderDto>> future = kafkaProductOrderDtoTemplate.send(topicNameOrders, productOrderDto.getIdx(), productOrderDto);
        future.addCallback(new ListenableFutureCallback<SendResult<String, ProductOrderDto>>() {

            @Override
            public void onSuccess(final SendResult<String, ProductOrderDto> sendResult) {
                LOG.info("send productOrderDto: {} ", productOrderDto);
            }

            @Override
            public void onFailure(final Throwable throwable) {
                LOG.error("unable to send productOrderDto= " + productOrderDto, throwable);
            }
        });

    }
}
