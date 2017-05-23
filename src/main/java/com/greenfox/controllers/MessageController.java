package com.greenfox.controllers;

import com.greenfox.model.Message;
import com.greenfox.model.Recieve;
import com.greenfox.model.Response;
import com.greenfox.model.User;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import java.sql.Timestamp;
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
//
//    long id = recieve.getMessage().getId();
//    String username = recieve.getMessage().getUser().getUsername();
//    String text = recieve.getMessage().getText();
//    Timestamp timestamp = recieve.getMessage().getTimestamp();
//
//    User newuser = new User(username);
//    userRepo.save(newuser);
//
//    Message newmessage = new Message(id,newuser,text,timestamp);
//    messageRepo.save(newmessage);

    return new Response("ok","message");
  }
}
