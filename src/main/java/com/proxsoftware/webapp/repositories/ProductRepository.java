package com.proxsoftware.webapp.repositories;

import com.proxsoftware.webapp.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends MongoRepository<Product, BigInteger> {

//   Product findById(BigInteger id);
}
