package com.greenfox.model;

import java.security.Key;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Log {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long Id;
  private String path;
  private String method;
  private Timestamp timestamp;
  private String loglevel;
  @Transient
  private Map requestData;

  public Log() {}

  public Log(HttpServletRequest request, String loglevel) {
    this.path = request.getServletPath();
    this.method = request.getMethod();
    this.timestamp = new Timestamp(request.getSession().getCreationTime());
    this.loglevel = loglevel;
    this.requestData = request.getParameterMap();
  }

  public String getRequestParamKey() {
    Map<String, String[]> requestDataCasted = (Map<String, String[]>) requestData;
    String key = "";
    for (Map.Entry<String, String[]> entry : requestDataCasted.entrySet()) {
       key = entry.getKey();
    }
    return key;
  }

  public String getRequestParamValue() {
    Map<String, String[]> requestDataCasted = (Map<String, String[]>) requestData;
    String paramValue = "";
    for (Map.Entry<String, String[]> entry : requestDataCasted.entrySet()) {
      for (String value: entry.getValue()) {
        paramValue = value;
      }
    }
    return paramValue;
  }

  @Override
  public String toString() {
    return timestamp + " " + loglevel + " " + "Request: " + path + " " + method + " " + getRequestParamKey() + "=" + getRequestParamValue();
  }

}
