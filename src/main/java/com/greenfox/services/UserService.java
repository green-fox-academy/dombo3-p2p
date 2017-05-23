package com.greenfox.services;

import com.greenfox.model.Account;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private Account currentAccount;

  public Account getCurrentAccount() {
    return currentAccount;
  }

  public void setCurrentAccount(Account currentAccount) {
    this.currentAccount = currentAccount;
  }
}
