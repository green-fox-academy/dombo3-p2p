package com.greenfox.model;

import java.util.Date;

public class Log {
  private String path;
  private String method;
  private Date date;
  private String loglevel;
  private String requestData;


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getLoglevel() {
    return loglevel;
  }

  public void setLoglevel(String loglevel) {
    this.loglevel = loglevel;
  }

  public String getRequestData() {
    return requestData;
  }

  public void setRequestData(String requestData) {
    this.requestData = requestData;
  }
}
