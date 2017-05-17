package com.greenfox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class P2pController {

  @RequestMapping("/")
  public String main() {
    return "index";
  }
  
}
