package com.wishlist.platform.dto;                                   // package path configuration

public class ExternalProductDto {                                    // data transfer object class representing external product data
    private Long id;                                                 // field storing product unique identifier
    private String title;                                            // field storing product title name
    private String price;                                            // field storing product price value

    // Getters and Setters
    public Long getId() { return id; }                               // getter method to retrieve product id
    public void setId(Long id) { this.id = id; }                     // setter method to update product id

    public String getTitle() { return title; }                       // getter method to retrieve product title
    public void setTitle(String title) { this.title = title; }       // setter method to update product title

    public String getPrice() { return price; }                       // getter method to retrieve product price
    public void setPrice(String price) { this.price = price; }       // setter method to update product price
}