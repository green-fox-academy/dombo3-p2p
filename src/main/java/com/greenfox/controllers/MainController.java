package com.greenfox.controllers;

import com.greenfox.exceptions.SimilarUserException;
import com.greenfox.exceptions.SimilarUserExceptionMessage;
import com.greenfox.exceptions.UsernameException;
import com.greenfox.exceptions.UsernameExceptionMessage;
import com.greenfox.model.Log;
import com.greenfox.model.User;
import com.greenfox.repository.LogRepo;
import com.greenfox.repository.UserRepo;
import com.greenfox.services.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  LogRepo logRepo;
  UserRepo userRepo;
  UserService userService;

  @Autowired
  public MainController(LogRepo logRepo, UserRepo userRepo, UserService userService) {
    this.logRepo = logRepo;
    this.userRepo = userRepo;
    this.userService = userService;
  }

  public final String CHAT_APP_UNIQUE_ID = "dombo3";
  public final String CHAT_APP_PEER_ADDRESS = "192.168.0.15";

  @RequestMapping("/")
  public String main(HttpServletRequest request) {
//    if (System.getenv("CHAT_APP_LOGLEVEL").equals("ERROR")) {
//      System.err.println("Error");
//    } else {
//      Log log = new Log(request,"INFO");
//      logRepo.save(log);
//    }

    if (userService.getCurrentUser() == null) {
      return "enter";
    } else {
      System.out.println(userService.getCurrentUser().getUsername());
      return "index";
    }

  }

  @GetMapping("/enter")
  public String enter() {
    return "enter";
  }

  @PostMapping("/enter")
  public String saveUser(@RequestParam(value = "username") String username) throws Exception {
    if (username.equals("")) { //if input field is empty
      throw new UsernameException();
    } else if (userRepo.count() == 0) { //if database is empty, create user
      System.out.println("Empty database case, create new user"); //log INFO
      User user = new User(username);   //Sad code, code duplicate because: cannot get into for cycle in empty database
      userService.setCurrentUser(user);
      userRepo.save(user);
    } else {
      for (User user : userRepo.findAll()) { //if find similar username
        if (user.getUsername().equals(username)) {
          System.out.println("EXCEPTION Find a similar username, please find another one"); //log ERROR
          throw new SimilarUserException();
        } else {
          System.out.println("Good choice Created a new user"); //log INFO
          User newuser = new User(username);
          userService.setCurrentUser(user);
          userRepo.save(newuser);
        }
      }
    }
    return "redirect:/";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable long id, @RequestParam("username") String username) throws Exception {
//    if (username.equals("")) {                //if input field is empty
//      throw new UsernameException();
//    } else {                                // CAnnot handle return in Exception
      User user = userRepo.findOne(id); //update in the Database;
      user.setUsername(username);
      userRepo.save(user);
      userService.getCurrentUser().setUsername(username); //update for the View
//    }
    return "redirect:/";
  }

  @ModelAttribute
  public void add(Model model) {
    model.addAttribute("user", userService.getCurrentUser());
  }


  @ExceptionHandler(UsernameException.class)
  public String UsernameException(Model model) {
    UsernameExceptionMessage error = new UsernameExceptionMessage("The username field is empty");
    // This should be a new Error Log, that is printed out and saved to the log database
    System.out.println(error.getError()); //log ERROR
    model.addAttribute("usernameerror",error);
    return "enter";
  }

  @ExceptionHandler(SimilarUserException.class)
  public String SimilarUserException(Model model) {
    SimilarUserExceptionMessage error = new SimilarUserExceptionMessage("There is a similar user in the database, please find another one");
    System.out.println(error.getError()); //log ERROR
    model.addAttribute("similarusererror", error);
    return "enter";
  }
}
