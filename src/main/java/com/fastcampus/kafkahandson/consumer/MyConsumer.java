package com.fastcampus.kafkahandson.consumer;

import com.fastcampus.kafkahandson.model.MyMessage;
import java.util.function.Consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyConsumer implements Consumer<Message<MyMessage>> {

  MyConsumer(){
    System.out.println("MyConsumer init!");
  }

  @Override
  public void accept(Message<MyMessage> message){
    System.out.println("Message arrived! -" +message.getPayload());
  }

}
