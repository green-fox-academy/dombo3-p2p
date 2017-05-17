package com.greenfox.controllers;

import com.greenfox.model.Log;
import com.greenfox.repository.LogRepo;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class P2pController {

  @Autowired
  LogRepo logRepo;

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
}
