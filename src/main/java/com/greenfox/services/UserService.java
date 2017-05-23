package com.greenfox.services;

import com.greenfox.model.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private Users currentUsers;

  public Users getCurrentUsers() {
    return currentUsers;
  }

  public void setCurrentUsers(Users currentUsers) {
    this.currentUsers = currentUsers;
  }
}
