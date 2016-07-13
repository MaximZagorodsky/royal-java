package com.proxsoftware.webapp.controller;

import com.proxsoftware.webapp.entity.Category;
import com.proxsoftware.webapp.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("api")
public class CategoryController extends AbstractController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/categories", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        log.info("categories get:" + allCategories);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }
}
