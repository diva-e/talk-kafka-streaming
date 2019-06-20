package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.archive;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.serde.SerdeService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Service;

@Service
public class ArchiveSinkProcessor {

    private final SerdeService serdeService;

    public ArchiveSinkProcessor(SerdeService serdeService) {
        this.serdeService = serdeService;
    }

    public void archiveOrder(final KStream<String, ProductOrderDto> maskedProductOrderStream) {

        // write masked stream to topic "archiv"
        maskedProductOrderStream.to("archiv", Produced.with(Serdes.String(),
                serdeService.getSerdeForProductOrderDto()));
    }
}
