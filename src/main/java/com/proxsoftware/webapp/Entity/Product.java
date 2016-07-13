package com.proxsoftware.webapp.entity;


import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Product {
    private String name;
    @Id()
    private BigInteger contentId;
    private String price;
    private String category;
    private String title;
    private String imageurl;
    private String description;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", contentId=" + contentId +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", description='" + description + '\'' +
                '}'+"\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {
    }

    public BigInteger getContentId() {
        return contentId;
    }

    public void setContentId(BigInteger contentId) {
        this.contentId = contentId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product(String name, String price, String category, String title, String imageurl, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.title = title;
        this.imageurl = imageurl;
        this.description = description;

    }
}
