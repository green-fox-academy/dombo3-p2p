package com.greenfox.model;

import java.sql.Timestamp;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Log {

  @Id
  @Setter
  @Getter
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long Id;
  private String path;
  private String method;
  private Timestamp timestamp;
  private String loglevel;
//  private Map requestData;

  public Log() {}

  public Log(HttpServletRequest request, String loglevel) {
    this.path = request.getServletPath();
    this.method = request.getMethod();
    this.timestamp = new Timestamp(request.getSession().getCreationTime());
    this.loglevel = loglevel;
//    this.requestData = request.getParameterMap();
  }

}
