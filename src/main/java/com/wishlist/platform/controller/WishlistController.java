package com.wishlist.platform.controller;                             // package path configuration

import com.wishlist.platform.entity.WishlistItem;                    // import wishlist item entity class
import com.wishlist.platform.service.WishlistService;                // import wishlist service for business logic execution
import org.springframework.security.core.annotation.AuthenticationPrincipal; // import annotation to get authenticated user details from session
import org.springframework.security.oauth2.core.user.OAuth2User;     // import oauth2 user class representing google login principal
import org.springframework.web.bind.annotation.*;                    // import spring mvc rest annotations

import java.util.List;                                               // import list collection utility

@RestController                                                      // marks class as a rest api controller returning json data
@RequestMapping("/api/wishlist")                                     // sets base url mapping for wishlist api endpoints
public class WishlistController {                                    // main wishlist controller class

    private final WishlistService wishlistService;                   // reference to wishlist service business layer

    public WishlistController(WishlistService wishlistService) {     // constructor-based dependency injection for service
        this.wishlistService = wishlistService;                      // initializes wishlist service instance
    }

    @GetMapping                                                      // maps http get requests to fetch user wishlist items
    public List<WishlistItem> getUserWishlist(@AuthenticationPrincipal OAuth2User principal) { // method to retrieve wishlist for logged-in user
        if (principal == null) {                                     // checks if user session principal is missing
            throw new RuntimeException("User is not authenticated"); // throws exception if unauthorized
        }
        String userEmail = principal.getAttribute("email");          // extracts user email attribute from google oauth2 session
        return wishlistService.getUserWishlist(userEmail);           // calls service to fetch list of items mapped to user email
    }

    @PostMapping                                                     // maps http post requests to add a new wishlist item
    public WishlistItem addItem(@RequestBody WishlistItem item, @AuthenticationPrincipal OAuth2User principal) { // method to save new item for user
        if (principal == null) {                                     // checks if user is authenticated
            throw new RuntimeException("User is not authenticated"); // throws exception if unauthenticated
        }
        String userEmail = principal.getAttribute("email");          // gets email from google principal session
        return wishlistService.addItem(item, userEmail);             // calls service to add item and trigger cache/websocket updates
    }

    @DeleteMapping("/{id}")                                          // maps http delete requests with an item id path variable
    public void deleteItem(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal) { // method to delete specific wishlist item
        if (principal == null) {                                     // checks if user is authenticated
            throw new RuntimeException("User is not authenticated"); // throws exception if unauthorized
        }
        String userEmail = principal.getAttribute("email");          // extracts email of currently logged-in user
        wishlistService.deleteItem(id, userEmail);                   // calls service to verify ownership, delete item, and clear cache
    }
}