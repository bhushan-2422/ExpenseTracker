package org.example.eventProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class UserInfoProducer {
    private final KafkaTemplate<String, UserInfoEvent> kafkaTemplate;

    @Value("${spring.kafka.topic-json.name}")
    private String topic_name;

    @Autowired
    UserInfoProducer(KafkaTemplate<String, UserInfoEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessageToKafka(UserInfoEvent userInfoEvent){
        Message<UserInfoEvent> message = MessageBuilder.withPayload(userInfoEvent).setHeader(KafkaHeaders.TOPIC, topic_name).build();
        kafkaTemplate.send(message);
    } 



}
