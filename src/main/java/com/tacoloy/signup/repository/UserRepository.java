package com.tacoloy.signup.repository;

import com.tacoloy.signup.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByusername(String username);
}
