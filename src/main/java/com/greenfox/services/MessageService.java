package com.greenfox.services;

import com.greenfox.model.ClientMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

  public static String CHAT_APP_UNIQUE_ID = System.getenv("CHAT_APP_UNIQUE_ID");
  public static String CHAT_APP_PEER_ADDRESSS = System.getenv("CHAT_APP_PEER_ADDRESSS");

  public List<String> validateMessage(ClientMessage clientMessage) {
    List<String> errors = new ArrayList<>();

    if (clientMessage.getMessage().getUsername() == null || clientMessage.getMessage().getUsername()
        .isEmpty()) {
      errors.add("message.username, ");
    }

    if (clientMessage.getMessage().getId() == 0L) {
      errors.add("message.id, ");
    }

    if (clientMessage.getMessage().getText() == null || clientMessage.getMessage().getText().isEmpty()) {
      errors.add("message.text, ");
    }

    if (clientMessage.getMessage().getTimestamp() == null) {
      errors.add("message.timestamp, ");
    }

    if (clientMessage.getClient().getId().isEmpty() || clientMessage.getClient().getId().equals("")) {
      errors.add("client.user");
    }

    return errors;
  }
}
