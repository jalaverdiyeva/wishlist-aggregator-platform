package com.wishlist.platform.dto;                                   // package path configuration

public class WishlistNotification {                                  // data transfer object class representing real-time wishlist notification events
    private String action; // e.g., "ADDED", "DELETED"               // field storing the action type description (added or deleted)
    private String itemName;                                         // field storing the name of the wishlist item
    private String userEmail;                                        // field storing the email of the user performing the action

    public WishlistNotification() {}                                 // default no-args constructor required for framework serialization

    public WishlistNotification(String action, String itemName, String userEmail) { // parameterized constructor to initialize all notification fields
        this.action = action;                                        // initializes action field
        this.itemName = itemName;                                    // initializes item name field
        this.userEmail = userEmail;                                  // initializes user email field
    }

    public String getAction() { return action; }                     // getter method to retrieve the action type
    public void setAction(String action) { this.action = action; }   // setter method to update the action type

    public String getItemName() { return itemName; }                 // getter method to retrieve the item name
    public void setItemName(String itemName) { this.itemName = itemName; } // setter method to update the item name

    public String getUserEmail() { return userEmail; }               // getter method to retrieve the user email
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; } // setter method to update the user email
}