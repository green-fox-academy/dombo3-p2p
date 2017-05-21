package com.greenfox.repository;

import com.greenfox.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
