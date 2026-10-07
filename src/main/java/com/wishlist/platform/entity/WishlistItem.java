package com.wishlist.platform.entity;                               // package path configuration

import jakarta.persistence.*;                                         // import jakarta persistence annotations for database entity mapping
import java.io.Serializable;                                        // import serializable interface for object serialization and caching

@Entity                                                               // marks this class as a database entity mapped to a table
@Table(name = "wishlist_items")                                       // specifies the database table name as wishlist_items
public class WishlistItem implements Serializable {                   // main wishlist item entity class implementing serializable

    private static final long serialVersionUID = 1L;                  // unique serial version identifier for serialization

    @Id                                                               // marks field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)               // configures auto-increment strategy for primary key generation
    private Long id;                                                  // unique primary key identifier for the wishlist item

    private String name;                                              // field storing the name of the wishlist item
    private String brand;                                             // field storing the brand of the wishlist item
    private Double price;                                             // field storing the price of the wishlist item
    private String category;                                          // field storing the category classification
    private String imageUrl;                                          // field storing the product image url

    @Column(name = "user_email", nullable = false)                    // specifies column mapping with non-null constraint for user email
    private String userEmail;                                         // field storing the email of the user who owns the item

    public WishlistItem() {}                                          // default empty constructor required by jpa

    public WishlistItem(String name, String brand, Double price, String category, String imageUrl, String userEmail) { // parameterized constructor to initialize all fields
        this.name = name;                                             // initializes item name
        this.brand = brand;                                           // initializes brand
        this.price = price;                                           // initializes price
        this.category = category;                                     // initializes category
        this.imageUrl = imageUrl;                                     // initializes image url
        this.userEmail = userEmail;                                   // initializes user email
    }

    // Getters and Setters
    public Long getId() {                                             // getter method to retrieve item id
        return id;                                                    // returns item id value
    }

    public void setId(Long id) {                                      // setter method to update item id
        this.id = id;                                                 // sets item id value
    }

    public String getName() {                                         // getter method to retrieve item name
        return name;                                                  // returns item name value
    }

    public void setName(String name) {                                // setter method to update item name
        this.name = name;                                             // sets item name value
    }

    public String getBrand() {                                        // getter method to retrieve brand
        return brand;                                                 // returns brand value
    }

    public void setBrand(String brand) {                              // setter method to update brand
        this.brand = brand;                                           // sets brand value
    }

    public Double getPrice() {                                        // getter method to retrieve price
        return price;                                                 // returns price value
    }

    public void setPrice(Double price) {                              // setter method to update price
        this.price = price;                                           // sets price value
    }

    public String getCategory() {                                     // getter method to retrieve category
        return category;                                              // returns category value
    }

    public void setCategory(String category) {                        // setter method to update category
        this.category = category;                                     // sets category value
    }

    public String getImageUrl() {                                     // getter method to retrieve image url
        return imageUrl;                                              // returns image url value
    }

    public void setImageUrl(String imageUrl) {                        // setter method to update image url
        this.imageUrl = imageUrl;                                     // sets image url value
    }

    public String getUserEmail() {                                    // getter method to retrieve user email
        return userEmail;                                             // returns user email value
    }

    public void setUserEmail(String userEmail) {                      // setter method to update user email
        this.userEmail = userEmail;                                   // sets user email value
    }
}