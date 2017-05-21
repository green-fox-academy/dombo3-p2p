package com.greenfox.exceptions;

import javax.jws.soap.SOAPBinding.Use;

public class UsernameExceptionMessage {
  private String error;

  public UsernameExceptionMessage(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
