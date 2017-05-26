package com.greenfox.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class LogTest {

  private Map<String, String[]> myMap = new HashMap<>();
  private Log myLog = new Log();

  @Before
  public void setup() throws Exception {
    String[] values={"FirstValue"};
    myMap.put("parameter", values);
    myLog.setRequestData(myMap);
  }


  @Test
  public void testRequestParamKey() {
    assertEquals("parameter", myLog.getRequestParamKey());
  }

  @Test
  public void testRequestParamValue() {
    assertEquals("FirstValue", myLog.getRequestParamValue());
  }
}