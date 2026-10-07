package com.wishlist.platform.entity;                               // package path configuration

import jakarta.persistence.*;                                         // import jakarta persistence annotations for database entity mapping

@Entity                                                               // marks this class as a database entity mapped to a table
@Table(name = "products")                                             // specifies the database table name as products
public class Product {                                                // main product entity class representing items in database

    @Id                                                               // marks field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)               // configures auto-increment strategy for primary key generation
    private Long id;                                                  // unique primary key identifier for the product

    private String name;                                              // field storing the product name
    private String brand;                                             // field storing the product brand
    private Double price;                                             // field storing the product price value
    private String productUrl;                                        // field storing the original product webpage url
    private String imageUrl;                                          // field storing the product image url
    private String category;                                          // field storing the product category classification
    private Boolean inStock;                                          // field storing stock availability status boolean

    public Product() {}                                               // default empty constructor required by jpa specification

    public Product(String name, String brand, Double price, String productUrl, String imageUrl, String category, Boolean inStock) { // parameterized constructor to initialize all fields
        this.name = name;                                             // initializes product name
        this.brand = brand;                                           // initializes product brand
        this.price = price;                                           // initializes product price
        this.productUrl = productUrl;                                 // initializes product url
        this.imageUrl = imageUrl;                                     // initializes image url
        this.category = category;                                     // initializes category
        this.inStock = inStock;                                       // initializes in-stock status
    }

    public Long getId() { return id; }                                // getter method to retrieve product id

    public String getName() { return name; }                          // getter method to retrieve product name
    public void setName(String name) { this.name = name; }            // setter method to update product name

    public String getBrand() { return brand; }                        // getter method to retrieve product brand
    public void setBrand(String brand) { this.brand = brand; }        // setter method to update product brand

    public Double getPrice() { return price; }                        // getter method to retrieve product price
    public void setPrice(Double price) { this.price = price; }        // setter method to update product price

    public String getProductUrl() { return productUrl; }              // getter method to retrieve product url
    public void setProductUrl(String productUrl) { this.productUrl = productUrl; } // setter method to update product url

    public String getImageUrl() { return imageUrl; }                  // getter method to retrieve image url
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; } // setter method to update image url

    public String getCategory() { return category; }                  // getter method to retrieve category
    public void setCategory(String category) { this.category = category; } // setter method to update category

    public Boolean getInStock() { return inStock; }                   // getter method to retrieve in-stock status
    public void setInStock(Boolean inStock) { this.inStock = inStock; } // setter method to update in-stock status
}