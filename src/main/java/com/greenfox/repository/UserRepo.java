package com.greenfox.repository;

import com.greenfox.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Account, Long> {
  Account findByUsername(String username);
}
