package com.greenfox.repository;

import com.greenfox.model.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {
  Iterable<Message> findAllByOrderByTimestampAsc();
}
