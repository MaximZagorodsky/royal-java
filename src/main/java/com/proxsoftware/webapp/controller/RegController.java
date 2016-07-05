package com.proxsoftware.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxsoftware.webapp.Entity.Category;
import com.proxsoftware.webapp.Entity.Product;
import com.proxsoftware.webapp.Entity.User;
import com.proxsoftware.webapp.repositories.CategoryRepository;
import com.proxsoftware.webapp.repositories.ContactRepository;
import com.proxsoftware.webapp.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
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
    private ContactRepository contactRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody User user) throws AuthenticationException {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            throw new AuthenticationException("Username and password must be provided");
        }
        contactRepository.save(user);
        String name = user.getUsername();
        List<User> all = contactRepository.findAll();
        log.info("username" + user.getUsername() + ", password: " + user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCategories() {
        List<Category> categoriesToFrontEnd = categoryRepository.findAll();
        log.info("categories get:" + categoriesToFrontEnd);
        return new ResponseEntity<>(categoriesToFrontEnd, HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
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

    @RequestMapping(value = "/product{productId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
//            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<?> getProductByCategory(@RequestParam String productId) {

        log.info("getProductById:id="+productId+", content:"+productRepository.findAll());

        return new ResponseEntity<>( productRepository.findOne(BigInteger.
                valueOf(Long.parseLong(productId))), HttpStatus.OK);
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
