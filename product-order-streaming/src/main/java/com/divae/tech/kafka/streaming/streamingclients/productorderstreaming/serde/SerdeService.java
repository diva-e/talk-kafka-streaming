package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.serde;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.billing.BillingDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.stockmanagement.StockManagementDto;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SerdeService {

    public Serde<ProductOrderDto> getSerdeForProductOrderDto() {
        final Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<ProductOrderDto> serializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", ProductOrderDto.class);
        serializer.configure(serdeProps, false);

        final Deserializer<ProductOrderDto> deserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", ProductOrderDto.class);
        deserializer.configure(serdeProps, false);

        final Serde<ProductOrderDto> serde = Serdes.serdeFrom(serializer, deserializer);

        return serde;

    }

    public Serde<BillingDto> getSerdeForBillingDto() {
        final Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<BillingDto> serializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", BillingDto.class);
        serializer.configure(serdeProps, false);

        final Deserializer<BillingDto> deserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", BillingDto.class);
        deserializer.configure(serdeProps, false);

        final Serde<BillingDto> serde = Serdes.serdeFrom(serializer, deserializer);

        return serde;

    }

    public Serde<StockManagementDto> getSerdeForStockmanagementDto() {
        final Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<StockManagementDto> serializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", StockManagementDto.class);
        serializer.configure(serdeProps, false);

        final Deserializer<StockManagementDto> deserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", StockManagementDto.class);
        deserializer.configure(serdeProps, false);

        final Serde<StockManagementDto> serde = Serdes.serdeFrom(serializer, deserializer);

        return serde;

    }


}

