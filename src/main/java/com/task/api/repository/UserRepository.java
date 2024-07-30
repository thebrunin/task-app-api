package com.task.api.repository;

import com.task.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ email: ?0}")
    User findByLogin(String login);
}
