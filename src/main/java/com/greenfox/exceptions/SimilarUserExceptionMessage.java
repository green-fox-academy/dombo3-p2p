package com.greenfox.exceptions;

public class SimilarUserExceptionMessage {
  private String error;

  public SimilarUserExceptionMessage(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
