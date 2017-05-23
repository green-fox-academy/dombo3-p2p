package com.greenfox.services;

import com.greenfox.model.Recieve;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageValidator {

  public List<String> validateMessage(Recieve recieve) {
    List<String> errors = new ArrayList<>();

    if (recieve.getMessage().getUsername() == null || recieve.getMessage().getUsername()
        .isEmpty()) {
      errors.add("message.username, ");
    }

    if (recieve.getMessage().getId() == 0L) {
      errors.add("message.id, ");
    }

    if (recieve.getMessage().getText() == null || recieve.getMessage().getText().isEmpty()) {
      errors.add("message.text, ");
    }

    if (recieve.getMessage().getTimestamp() == null) {
      errors.add("message.timestamp, ");
    }

    if (recieve.getClient().getId().isEmpty() || recieve.getClient().getId().equals("")) {
      errors.add("client.user");
    }

    return errors;
  }

}
