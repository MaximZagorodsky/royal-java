package com.proxsoftware.webapp.repositories;

import com.proxsoftware.webapp.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CategoryRepository extends MongoRepository<Category, BigInteger> {
}
