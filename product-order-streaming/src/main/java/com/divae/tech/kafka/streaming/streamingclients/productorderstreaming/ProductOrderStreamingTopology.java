package com.divae.tech.kafka.streaming.streamingclients.productorderstreaming;

import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.archive.ArchiveSinkProcessor;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.billing.BillingSinkProcesser;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.marketing.MarketingSinkProcessor;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.masking.MaskingStreamProcessor;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderDto;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.productOrder.ProductOrderSourceProcessor;
import com.divae.tech.kafka.streaming.streamingclients.productorderstreaming.stockmanagement.StockManagementSinkProcessor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class ProductOrderStreamingTopology {

    private static final Logger LOG = LoggerFactory.getLogger(ProductOrderStreamingTopology.class);


    private final ProductOrderSourceProcessor productOrderSourceProcessor;
    private final BillingSinkProcesser billingSinkProcesser;
    private final MaskingStreamProcessor maskingStreamProcessor;
    private final StockManagementSinkProcessor stockManagementSinkProcessor;
    private final ArchiveSinkProcessor archiveSinkProcessor;
    private final MarketingSinkProcessor marketingSinkProcessor;


    public ProductOrderStreamingTopology(ProductOrderSourceProcessor productOrderSourceProcessor, BillingSinkProcesser billingSinkProcesser, MaskingStreamProcessor maskingStreamProcessor, StockManagementSinkProcessor stockManagementSinkProcessor, ArchiveSinkProcessor archiveSinkProcessor, MarketingSinkProcessor marketingSinkProcessor) {
        this.productOrderSourceProcessor = productOrderSourceProcessor;
        this.billingSinkProcesser = billingSinkProcesser;
        this.maskingStreamProcessor = maskingStreamProcessor;
        this.stockManagementSinkProcessor = stockManagementSinkProcessor;
        this.archiveSinkProcessor = archiveSinkProcessor;
        this.marketingSinkProcessor = marketingSinkProcessor;
    }


    public void streamIncommingProductOrders() {

        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "id_productOrders_01");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        streamsConfiguration.put("auto.offset.reset", "earliest");


        final StreamsBuilder builder = new StreamsBuilder();

        // Get incomming ProductOrder stream
        final KStream<String, ProductOrderDto> productOrderStream =
                productOrderSourceProcessor.getIncommingProductOrderStream(builder);

        // extract billing information
        billingSinkProcesser.extractBillingInformation(productOrderStream);

        // mask credit card information
        final KStream<String, ProductOrderDto> maskedProductOrderStream =
                maskingStreamProcessor.generateMaskedStream(productOrderStream);

        // archive masked order stream
        archiveSinkProcessor.archiveOrder(maskedProductOrderStream);

        // extract information for stock management
        stockManagementSinkProcessor.extractStockManagementInformation(maskedProductOrderStream);

        // extract information for marketing
        marketingSinkProcessor.filterTopOrders(maskedProductOrderStream);


        // start streaming
        final KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), streamsConfiguration);
        kafkaStreams.start();
        LOG.info("start reading incomming product orders ....");


    }
}
