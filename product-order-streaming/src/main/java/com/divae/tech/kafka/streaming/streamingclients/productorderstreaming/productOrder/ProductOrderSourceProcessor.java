package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.serde.SerdeService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderSourceProcessor {

    private final SerdeService serdeService;

    public ProductOrderSourceProcessor(SerdeService serdeService) {
        this.serdeService = serdeService;
    }


    public KStream<String, ProductOrderDto> getIncommingProductOrderStream(final StreamsBuilder builder) {
        return builder.stream("products_orders", Consumed.with(Serdes.String(),
                serdeService.getSerdeForProductOrderDto()));
    }
}
