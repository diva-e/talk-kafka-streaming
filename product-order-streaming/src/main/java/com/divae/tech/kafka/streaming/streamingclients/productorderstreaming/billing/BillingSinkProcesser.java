package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.billing;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.serde.SerdeService;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

@Component
public class BillingSinkProcesser {

    private final SerdeService serdeService;

    public BillingSinkProcesser(SerdeService serdeService) {
        this.serdeService = serdeService;
    }


    public void extractBillingInformation(final KStream<String, ProductOrderDto> productOrderStream) {
        // create a new stream with billing information
        // map takes one record and produces one record.
        final KStream<String, BillingDto> billingStream =
                productOrderStream.map((key, value) -> extractBillinginformationFromOrder(value));

        // write stream to topic "billings"
        billingStream.to("billings", Produced.with(Serdes.String(), serdeService.getSerdeForBillingDto()));

    }

    private KeyValue<String, BillingDto> extractBillinginformationFromOrder(ProductOrderDto value) {

        final BillingDto billingDto = new BillingDto();
        billingDto.setAmount(value.getTotalAmount());
        billingDto.setCreditCardNumber(value.getCreditCardNumber());
        billingDto.setName(value.getName());

        return KeyValue.pair(value.getIdx(), billingDto);
    }

}
