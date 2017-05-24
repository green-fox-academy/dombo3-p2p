package com.greenfox.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.format.datetime.joda.ReadablePartialPrinter;

public class Response {
  private String status;
  @JsonInclude(Include.NON_NULL)
  private String message;

  public Response() {};

  public Response(String status) {
    this.status = status;
  }

  public Response(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
