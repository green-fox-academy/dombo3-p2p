package com.greenfox.repository;

import com.greenfox.model.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepo extends CrudRepository<Log, Long> {
}
