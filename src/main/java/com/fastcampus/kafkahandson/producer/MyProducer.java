package com.fastcampus.kafkahandson.producer;

import com.fastcampus.kafkahandson.model.MyMessage;
import java.util.function.Supplier;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;

@Component
public class MyProducer implements Supplier<Flux<Message<MyMessage>>> {

  MyProducer(){
    System.out.println("MyProducer init!");
  }


  private final Sinks.Many<Message<MyMessage>> sinks=Sinks.many().unicast().onBackpressureBuffer();

  public void sendMessage(MyMessage myMessage){

    Message<MyMessage> message= MessageBuilder
        .withPayload(myMessage)
        .setHeader(KafkaHeaders.KEY,String.valueOf(myMessage.getAge()))
        .build();
    sinks.emitNext(message, EmitFailureHandler.FAIL_FAST);
  }

  @Override
  public Flux<Message<MyMessage>> get(){
    return sinks.asFlux();
  }



}
