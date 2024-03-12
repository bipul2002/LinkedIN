package com.elasticsearch.elasticsearch.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Rabbit")
public class RabbitNQController {

    private  final RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitNQController(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send-message")
    public  String sendMessage(@RequestBody String message){
        rabbitTemplate.convertAndSend("queueData",message);
        return  "message sent to the queue:" + message;
    }

}
