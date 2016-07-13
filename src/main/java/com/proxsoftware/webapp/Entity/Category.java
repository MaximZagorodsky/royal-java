package com.proxsoftware.webapp.entity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Document
public class Category {
    @Field("category")
    private String categoryName;
    private List<String> subCategories = new ArrayList();
    private BigInteger id;



    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", subCategories=" + subCategories +
                ", id=" + id +
                '}';
    }

    public Category(@Value("#root.category")  String category) {
        this.categoryName = category;
        this.subCategories.add("Gear Pump");
        this.subCategories.add("RVD");

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
