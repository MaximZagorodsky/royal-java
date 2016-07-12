package com.proxsoftware.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxsoftware.webapp.Entity.Category;
import com.proxsoftware.webapp.Entity.Product;
import com.proxsoftware.webapp.repositories.CategoryRepository;
import com.proxsoftware.webapp.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class RegController {
    private Logger log = LoggerFactory.getLogger(RegController.class);


    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ApplicationContext applicationContext;



    @RequestMapping(value = "/categories", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCategories() {
        List<Category> allCategories = categoryRepository.findAll();
//        log.info("categories get:" + allCategories);
        System.out.println("AppContext: "+applicationContext.getClassLoader().getClass());
        System.out.println("AppContext: "+applicationContext.getClass());
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        log.info("getAllProducts:" + productList);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    private List<Product> createProducts() {
        List<Product> productList = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productList = objectMapper.readValue(
                    new File("C:/Angular_2_projects/book_library/app_ts/content/content.json"),
                    new TypeReference<List<Product>>() {
                    });
            BigInteger id = BigInteger.valueOf(0L);
            for (Product prod : productList) {
                prod.setContentId(id);
                id = id.add(BigInteger.valueOf(1L));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
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

    private List<Category> createCategories() {
        List<Category> list = new ArrayList<>();


        list.add(new Category("Components"));
        list.add(new Category("Hydraulics"));
        list.add(new Category("Bottle"));
        list.add(new Category("Ikonka"));
        list.add(new Category("Chashka"));
        list.add(new Category("Marka"));
        list.add(new Category("Pult"));
        list.add(new Category("Vutyagka"));

        System.out.println(list);
        return list;
    }


}
