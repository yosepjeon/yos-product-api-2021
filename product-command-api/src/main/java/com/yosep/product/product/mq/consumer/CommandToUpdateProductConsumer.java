package com.yosep.product.product.mq.consumer;

import com.yosep.product.product.service.ProductReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class CommandToUpdateProductConsumer {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "test-topic";

    private final ReceiverOptions<Integer, String> receiverOptions;
    private final SimpleDateFormat dateFormat;
    private Map<String, Object> props;

    private Disposable disposable;

    private final ProductReactiveService productReactiveService;

    public CommandToUpdateProductConsumer(ProductReactiveService productReactiveService) {
        this.productReactiveService = productReactiveService;

        props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "sample-consumer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        receiverOptions = ReceiverOptions.create(props);
        dateFormat = new SimpleDateFormat("HH:mm:ss:SSS z dd MMM yyyy");
        final ReceiverOptions<String, String> options = ReceiverOptions.<String, String>create(props)
                .subscription(List.of(TOPIC));

        KafkaReceiver.create(options)
                .receive()
                .doOnSubscribe( record ->
                        System.out.println(record.toString())
                )
                .doOnError(e -> {
                    log.error("Kafka read error");
                    new CommandToUpdateProductConsumer(productReactiveService);     // 에러 발생 시, consumer가 종료되고 재시작할 방법이 없기 때문에 error시 재시작
                })
                .subscribe(record -> {
                    log.info(record.toString());
                    new CustomSubscriber(productReactiveService.test(record));
                });
    }

    public Flux<ReceiverRecord<String, String>> consumer(final String topic) {
        final ReceiverOptions<String, String> options = ReceiverOptions.<String, String>create(props)
                .subscription(List.of(topic));

        return KafkaReceiver.create(options)
                .receive();
    }

    public Disposable consumeMessages(String topic, CountDownLatch latch) {

        ReceiverOptions<Integer, String> options = receiverOptions.subscription(Collections.singleton(topic))
                .addAssignListener(partitions -> log.debug("onPartitionsAssigned {}", partitions))
                .addRevokeListener(partitions -> log.debug("onPartitionsRevoked {}", partitions));
        Flux<ReceiverRecord<Integer, String>> kafkaFlux = KafkaReceiver.create(options).receive();
        return kafkaFlux.subscribe(record -> {
            ReceiverOffset offset = record.receiverOffset();
            System.out.printf("Received message: topic-partition=%s offset=%d timestamp=%s key=%d value=%s\n",
                    offset.topicPartition(),
                    offset.offset(),
                    dateFormat.format(new Date(record.timestamp())),
                    record.key(),
                    record.value());
            offset.acknowledge();
            latch.countDown();
        });
    }

//    public static void main(String[] args) throws Exception {
//        int count = 20;
//        CountDownLatch latch = new CountDownLatch(count);
//        SampleConsumer consumer = new SampleConsumer(BOOTSTRAP_SERVERS);
//        Disposable disposable = consumer.consumeMessages(TOPIC, latch);
//        latch.await(10, TimeUnit.SECONDS);
//        disposable.dispose();
//    }
}
