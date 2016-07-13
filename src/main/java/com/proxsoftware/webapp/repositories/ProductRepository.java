package com.proxsoftware.webapp.repositories;

import com.proxsoftware.webapp.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, BigInteger> {

    List<Product> findByCategory(String searchString,  PageRequest pageRequest);

    List<Product> findByNameLike(String searchString, PageRequest pageRequest);


}
