package com.greenfox.controllers;

import com.greenfox.model.Log;
import com.greenfox.model.User;
import com.greenfox.repository.LogRepo;
import com.greenfox.repository.UserRepo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  LogRepo logRepo;
  UserRepo userRepo;

  @Autowired
  public MainController(LogRepo logRepo, UserRepo userRepo) {
    this.logRepo = logRepo;
    this.userRepo = userRepo;
  }

  public final String CHAT_APP_UNIQUE_ID = "dombo3";
  public final String CHAT_APP_PEER_ADDRESS = "192.168.0.15";

  @RequestMapping("/")
  public String main(HttpServletRequest request) {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.err.println("Error");
    } else {
      Log log = new Log(request,"INFO");
      logRepo.save(log);
    }
    return "index";
  }

  @GetMapping("/enter")
  public String enter() {
    return "enter";
  }

  @PostMapping("/enter")
  public String saveUser(@RequestParam("username") String username) {
    userRepo.save(new User(username));
    return "redirect:/";
  }
}
