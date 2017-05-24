package com.greenfox.controllers;

import com.greenfox.model.ClientMessage;
import com.greenfox.model.Response;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import com.greenfox.services.MessageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MessageController {

  MessageService messageService;
  MessageRepo messageRepo;
  UserRepo userRepo;

  public MessageController(MessageService messageService, MessageRepo messageRepo, UserRepo userRepo) {
    this.messageService = messageService;
    this.messageRepo = messageRepo;
    this.userRepo = userRepo;
  }

  @PostMapping("/api/message/receive")
  @CrossOrigin("*")
  public Response receiveMessage(@RequestBody ClientMessage clientMessage) {

    clientMessage.getClient();

    String errormessage = "";
    for (String error : messageService.validateMessage(clientMessage)) {
      System.out.println(clientMessage.getMessage().getText());
      errormessage += error;
      System.out.println(error);
    }

    if (clientMessage.getClient().getId().equals(MessageService.CHAT_APP_UNIQUE_ID)) {
      System.out.println("Got my own message");
      return new Response("ok", "Thanks for my own message");
    } else if (errormessage.equals("")) {
      messageRepo.save(clientMessage.getMessage());
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(MessageService.CHAT_APP_PEER_ADDRESSS, clientMessage, Response.class);
      return new Response("ok");
    } else {
      return new Response("error", "Missing field(s): " + errormessage);
    }
  }
}
