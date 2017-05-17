package com.greenfox.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class P2pController {

  @RequestMapping("/")
  public String main(HttpServletRequest request) {
    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
      System.err.println(request.getSession().getCreationTime());
    } else {
      System.out.println("log everything");
    }
    return "index";
  }
}
