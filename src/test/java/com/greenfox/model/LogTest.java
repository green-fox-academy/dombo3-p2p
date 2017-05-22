package com.greenfox.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class LogTest {

  Map<String, String[]> myMap = new HashMap<>();
  Log myLog = new Log();

  @Test
  public void testRequestParamKey() {
    String[] values={"FirstValue"};
    myMap.put("parameter", values);
    myLog.setRequestData(myMap);
//    myLog.RequestDataToString();
    assertEquals("parameter", myLog.getRequestParamKey());
  }

  @Test
  public void testRequestParamValue() {
    String[] values={"FirstValue"};
    myMap.put("parameter", values);
    myLog.setRequestData(myMap);
//    myLog.RequestDataToString();
    assertEquals("FirstValue", myLog.getRequestParamValue());
  }
}