package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.stockmanagement;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderItemDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.serde.SerdeService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockManagementSinkProcessor {

    private final SerdeService serdeService;

    public StockManagementSinkProcessor(SerdeService serdeService) {
        this.serdeService = serdeService;
    }

    public void extractStockManagementInformation(final KStream<String, ProductOrderDto>
                                                          maskedProductOrderStream) {

        // create a stream with information for stock management
        // flatMapValues: takes one record and produces zero, one, or more records
        final KStream<String, StockManagementDto> stockManagementStream =
                maskedProductOrderStream.flatMapValues(value -> extractValuesForStockManagement(value));

        // write stream to topic "stockmanagement"
        stockManagementStream.to("stockmanagement", Produced.with(Serdes.String(),
                serdeService.getSerdeForStockmanagementDto()));

    }

    private List<StockManagementDto> extractValuesForStockManagement(ProductOrderDto productOrderDto) {
        final List<StockManagementDto> result = new ArrayList<>();
        for (final ProductOrderItemDto itemDto : productOrderDto.getOrderItems()) {
            final StockManagementDto stockManagementDto = new StockManagementDto();
            stockManagementDto.setProductIdx(itemDto.getProductDto().getIdx());
            stockManagementDto.setNumberOfItems(itemDto.getQuantity());

            result.add(stockManagementDto);
        }


        return result;
    }

}
