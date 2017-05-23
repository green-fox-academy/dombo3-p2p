package com.greenfox.controllers;

import com.greenfox.model.Recieve;
import com.greenfox.model.Response;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

  MessageRepo messageRepo;
  UserRepo userRepo;

  public MessageController(MessageRepo messageRepo, UserRepo userRepo) {
    this.messageRepo = messageRepo;
    this.userRepo = userRepo;
  }

  @PostMapping("/api/message/recieve")
  public Response recieveMessage(@RequestBody Recieve recieve) {

    recieve.getClient();
    messageRepo.save(recieve.getMessage());

    return new Response("ok","message");
  }
}
