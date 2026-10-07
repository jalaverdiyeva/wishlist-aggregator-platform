package com.wishlist.platform.entity;                               // package path configuration

import jakarta.persistence.*;                                         // import jakarta persistence annotations for database mapping

@Entity                                                               // marks this class as a database entity mapped to a table
@Table(name = "users")                                                // specifies the database table name as users
public class User {                                                   // main user entity class representing application users

    @Id                                                               // marks field as the primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)               // configures auto-increment strategy for primary key generation
    private Long id;                                                  // unique primary key identifier for the user

    @Column(unique = true)                                            // specifies that the column value must be unique in database
    private String email;                                             // field storing the user email address

    @Column(unique = true)                                            // specifies that the column value must be unique
    private String username;                                          // field storing the unique username handle

    private String password;                                          // field storing the user password

    public User() {}                                                  // default empty constructor required by jpa

    public User(String email, String username, String password) {     // parameterized constructor to initialize user fields
        this.email = email;                                           // initializes user email
        this.username = username;                                     // initializes username
        this.password = password;                                     // initializes password
    }

    // Getters and Setters
    public Long getId() { return id; }                                // getter method to retrieve user id
    public String getEmail() { return email; }                        // getter method to retrieve user email
    public void setEmail(String email) { this.email = email; }        // setter method to update user email
    public String getUsername() { return username; }                  // getter method to retrieve username
    public void setUsername(String username) { this.username = username; } // setter method to update username
    public String getPassword() { return password; }                  // getter method to retrieve password
    public void setPassword(String password) { this.password = password; } // setter method to update password
}