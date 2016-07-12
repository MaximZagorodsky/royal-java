package com.proxsoftware.webapp.controller;

import com.proxsoftware.webapp.Entity.Product;
import com.proxsoftware.webapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController extends AbstractController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/products", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        log.info("getAllProducts:" + productList);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @RequestMapping(value = "/productsByCategory{category}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<?> getProductsByCategory(@RequestParam String category) {
        List<Product> findBycategory = !category.equals("undefined") ?
                productRepository.findByCategory(category, new PageRequest(0, 10)) :
                productRepository.findAll();
        log.info("getProductsByCategory:category = " + category + ", content:" + findBycategory);

        return new ResponseEntity<>(findBycategory, HttpStatus.OK);
    }

    @RequestMapping(value = "/productBySearch{searchQuery}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getProductBySearch(@RequestParam String searchQuery) {
        List<Product> searchByNameLike = productRepository.findByNameLike(
                searchQuery.toUpperCase(), new PageRequest(0, 10));
        log.info("getProductById:id=" + searchQuery + ", content:" + searchByNameLike);
        return new ResponseEntity<>(searchByNameLike, HttpStatus.OK);
    }

    @RequestMapping(value = "/product{productId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getProductById(@RequestParam String productId) {
        Product product = productRepository.findOne(BigInteger.valueOf(Long.parseLong(productId)));
        log.info("getProductById:id=" + productId + ", content:" + product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @RequestMapping(value = "/createProduct", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> login(@RequestBody Product product) throws AuthenticationException {
        productRepository.save(product);
        List<Product> byNameLike = productRepository.findByNameLike(product.getName(), new PageRequest(0, 10));
        System.out.println(byNameLike);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
