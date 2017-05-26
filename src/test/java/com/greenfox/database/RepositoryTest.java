package com.greenfox.database;

import static org.assertj.core.api.Assertions.*;

import com.greenfox.model.Account;
import com.greenfox.model.Message;
import com.greenfox.repository.MessageRepo;
import com.greenfox.repository.UserRepo;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.resources.Messages;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private MessageRepo messageRepo;

  @Test
  public void testExample() throws Exception {
    entityManager.persist(new Account("dombo3"));
    Account account = this.userRepo.findByUsername("dombo3");
    assertThat(account.getUsername()).isEqualTo("dombo3");
  }

  @Test
  public void messageRepoTestExample() throws Exception {
    entityManager.persist(new Message("dombo3","Hello"));
    Iterable<Message> messages = messageRepo.findAll();
    Message firstMessage = Lists.newArrayList(messages).get(0);
    assertThat(firstMessage.getText()).isEqualTo("Hello");
  }
}
