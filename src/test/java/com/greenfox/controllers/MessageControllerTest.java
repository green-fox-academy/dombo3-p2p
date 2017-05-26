package com.greenfox.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.P2pApplication;
import com.greenfox.model.Client;
import com.greenfox.model.ClientMessage;
import com.greenfox.model.Message;
import com.greenfox.repository.MessageRepo;
import org.assertj.core.util.Lists;
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
  private String text;

  @Autowired
  private MessageRepo messageRepo;
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void ReceiveValidatedMessage() throws Exception {
    Client client = new Client("zspadar");
    Message message = new Message("Peterke","Dombo3 message from mock test");
    ClientMessage clientMessage = new ClientMessage(message, client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(clientMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("ok"));
  }

  @Test
  public void ReceiveUnValidMessage() throws Exception {
    Client client = new Client("zspadar");
    Message message = new Message("Peterke","");
    ClientMessage clientMessage = new ClientMessage(message, client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(clientMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonInput))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("error"))
        .andExpect(jsonPath("$.message").value("Missing field(s): message.text, "));
  }

  @Test
  public void MessageRepoTest() throws Exception {
    Client client = new Client("zspadar");
    Message message = new Message("Peterke","");
    ClientMessage clientMessage = new ClientMessage(message, client);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInput = mapper.writeValueAsString(clientMessage);

    mockMvc.perform(post("/api/message/receive")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonInput));

    Iterable<Message> messages = messageRepo.findAll();
    Message firstMessage = Lists.newArrayList(messages).get(0);
    assertThat(firstMessage.getText()).isEqualTo("Dombo3 message from mock test");
  }

  @Test
  public void getMessagesForAndroid() throws Exception {

    mockMvc.perform(get("/api/message")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
//        .andExpect(jsonPath("$.messages").value("[{\"id\": 7655482,\"username\": \"petikake\",\"text\": \"Anroid rulez\",\"timestamp\": 1322018752992}]"));
  }
}
