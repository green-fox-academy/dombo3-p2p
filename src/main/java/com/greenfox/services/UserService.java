package com.greenfox.services;

import com.greenfox.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private User currentUser;

  public User getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(User currentUser) {
    this.currentUser = currentUser;
  }
}
