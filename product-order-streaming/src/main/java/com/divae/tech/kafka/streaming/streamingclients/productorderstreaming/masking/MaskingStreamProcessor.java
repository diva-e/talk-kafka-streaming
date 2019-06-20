package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.masking;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

@Service
public class MaskingStreamProcessor {

    public KStream<String, ProductOrderDto> generateMaskedStream(final KStream<String, ProductOrderDto>
                                                                         productOrderDtoStream) {
        // create a new stream with masked credit card numbers
        final KStream<String, ProductOrderDto> maskedProductOrderStream =
                productOrderDtoStream.map((key, value) -> maskProductOrders(key, value));

        return maskedProductOrderStream;

    }


    private KeyValue<String, ProductOrderDto> maskProductOrders(String key, ProductOrderDto value) {
        final String maskCreditCardInformation = maskCreditCardInformation(value.getCreditCardNumber());
        value.setCreditCardNumber(maskCreditCardInformation);

        return KeyValue.pair(key, value);
    }

    private String maskCreditCardInformation(String creditCardNumber) {
        return "XXXXXXXX" + creditCardNumber.substring(creditCardNumber.length() - 4);
    }


}
