package com.greenfox.repository;

import com.greenfox.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Long> {
  Users findByUsername(String username);
}
