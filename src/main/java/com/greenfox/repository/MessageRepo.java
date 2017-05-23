package com.greenfox.repository;

import com.greenfox.model.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
  Iterable<Message> findAllByOrderByTimestampAsc();
}
