package com.greenfox.controllers;

import com.greenfox.model.Recieve;
import com.greenfox.model.Response;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import com.greenfox.services.MessageValidator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
  public Response recieveMessage(@RequestBody Recieve recieve) {

    recieve.getClient();

    String message = "";
    for (String error : messageValidator.validateMessage(recieve)) {
      message += error;
    }

    if (message.equals("")) {
      messageRepo.save(recieve.getMessage());
      return new Response("ok",null);
    } else {
      return new Response("error", "Missing field(s): " + message);
    }
  }
}
