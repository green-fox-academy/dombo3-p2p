package com.greenfox.controllers;

import com.greenfox.model.Client;
import com.greenfox.model.ClientMessage;
import com.greenfox.model.Response;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import com.greenfox.services.MessageValidator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MessageController {

  MessageValidator messageValidator;
  MessageRepo messageRepo;
  UserRepo userRepo;

  public MessageController(MessageValidator messageValidator, MessageRepo messageRepo, UserRepo userRepo) {
    this.messageValidator = messageValidator;
    this.messageRepo = messageRepo;
    this.userRepo = userRepo;
  }

  @PostMapping("/api/message/recieve")
  @CrossOrigin("*")
  public Response receiveMessage(@RequestBody ClientMessage clientMessage) {

    clientMessage.getClient();

    String message = "";
    for (String error : messageValidator.validateMessage(clientMessage)) {
      message += error;
    }

    if (message.equals("")) {
      messageRepo.save(clientMessage.getMessage());
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject("https://stegmarb-peertopeer.herokuapp.com/api/message/receive",clientMessage,Response.class);
      return new Response("ok",null);
    } else {
      return new Response("error", "Missing field(s): " + message);
    }
  }
}
