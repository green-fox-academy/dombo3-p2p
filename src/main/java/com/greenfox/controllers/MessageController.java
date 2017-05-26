package com.greenfox.controllers;

import com.greenfox.model.Client;
import com.greenfox.model.ClientMessage;
import com.greenfox.model.Message;
import com.greenfox.model.Messages;
import com.greenfox.model.Response;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import com.greenfox.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class MessageController {

  private MessageService messageService;
  private MessageRepo messageRepo;
  private UserRepo userRepo;

  @Autowired
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
      errormessage += error;
    }

    if (clientMessage.getClient().getId().equals(MessageService.CHAT_APP_UNIQUE_ID)) {
      return new Response("ok", "Thanks for my own message");
    } else if (errormessage.equals("")) {
      messageRepo.save(clientMessage.getMessage());
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.postForObject(MessageService.CHAT_APP_PEER_ADDRESS, clientMessage, Response.class);
      return new Response("ok", null);
    } else {
      return new Response("error", "Missing field(s): " + errormessage);
    }
  }

  @GetMapping("api/message")
  public Messages collectAllMessage() {
    messageRepo.save(new Message("en","Android rulez"));
    return new Messages(messageRepo.findAllByOrderByTimestampAsc(), new Client("dombo3"));
  }
}
