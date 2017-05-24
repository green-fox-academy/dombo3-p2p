package com.greenfox.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.google.gson.Gson;
import com.greenfox.P2pApplication;
import com.greenfox.model.Client;
import com.greenfox.model.ClientMessage;
import com.greenfox.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = P2pApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class MessageControllerTest {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void ReceiveValidatedMessage() throws Exception {
    String text = "Hello";
    long id = 1000000 + (long)(Math.random() * 1000000);
    String username = "steg";

    Client client = new Client("steg");
    Message message = new Message(username,text);
    ClientMessage clientMessage = new ClientMessage(message,client);

    Gson gson = new Gson();
    String json = gson.toJson(clientMessage);

    System.out.println(json);
    mockMvc.perform(post("/api/message/receive").contentType(MediaType.APPLICATION_JSON).content(
        "{ \"message\": {\"id\": 7655482,\"username\": \"EggDice\",\"text\": \"How you doin'?\",\"timestamp\": 1322018752992},\"client\": {\"id\": \"EggDice\"}}"
//          json
    )).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ok"));
  }
}
