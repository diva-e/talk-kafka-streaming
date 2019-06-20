package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.marketing;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.serde.SerdeService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Service;

@Service
public class MarketingSinkProcessor {

    private final SerdeService serdeService;

    public MarketingSinkProcessor(SerdeService serdeService) {
        this.serdeService = serdeService;
    }

    public void filterTopOrders(final KStream<String, ProductOrderDto> maskedProductOrderStream) {

        // create a filtered stream where value of the order is gt 150
        final KStream<String, ProductOrderDto> topOrderStream =
                maskedProductOrderStream.filter((key, value) -> value.getTotalAmount() > 150);

        // write stream to topic "toporders"
        topOrderStream.to("toporders", Produced.with(Serdes.String(), serdeService.getSerdeForProductOrderDto()));

    }


}
