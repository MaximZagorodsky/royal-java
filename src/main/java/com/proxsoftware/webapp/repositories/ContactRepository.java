package com.proxsoftware.webapp.repositories;

import com.proxsoftware.webapp.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ContactRepository extends MongoRepository<User,BigInteger> {

}
