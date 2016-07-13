package com.proxsoftware.webapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proxsoftware.webapp.entity.Category;
import com.proxsoftware.webapp.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractController {
    protected Logger log = LoggerFactory.getLogger(ProductController.class);

    protected List<Product> createProducts() {
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

    protected List<Category> createCategories() {
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
