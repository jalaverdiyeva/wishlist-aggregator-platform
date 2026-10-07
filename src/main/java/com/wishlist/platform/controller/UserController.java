package com.wishlist.platform.controller;                             // package path configuration

import com.wishlist.platform.entity.User;                            // import user entity class
import com.wishlist.platform.repository.UserRepository;              // import user repository interface for database queries
import org.springframework.beans.factory.annotation.Autowired;       // import autowired for dependency injection
import org.springframework.http.ResponseEntity;                      // import responseentity to handle http responses and statuses
import org.springframework.security.core.annotation.AuthenticationPrincipal; // import annotation to get logged-in user principal
import org.springframework.security.oauth2.core.user.OAuth2User;     // import oauth2 user object containing google login attributes
import org.springframework.web.bind.annotation.*;                    // import spring mvc annotations for rest controllers

import java.util.HashMap;                                            // import hashmap collection utility
import java.util.Map;                                                // import map interface
import java.util.Optional;                                           // import optional wrapper to handle null values safely

@RestController                                                      // marks this class as a rest api controller returning json
@RequestMapping("/api/user")                                         // sets the base url path for user-related endpoints
public class UserController {                                        // main user controller class

    @Autowired                                                       // injects user repository automatically
    private UserRepository userRepository;                           // reference to user repository for database operations

    @GetMapping("/me")                                               // maps http get requests to fetch current logged-in user details
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) { // method returning map of user attributes from session
        if (principal == null) {                                     // checks if user is not authenticated
            return Map.of();                                         // returns empty map if no user is logged in
        }

        String email = principal.getAttribute("email");              // extracts email attribute from google oauth2 session
        String name = principal.getAttribute("name");                // extracts name attribute from google oauth2 session
        String picture = principal.getAttribute("picture");          // extracts picture url attribute from google oauth2 session

        Optional<User> existingUser = userRepository.findByEmail(email); // checks database if user already exists by email

        Map<String, Object> response = new HashMap<>();              // creates response map for frontend client
        response.put("name", name != null ? name : "");              // puts name into response map or empty string if null
        response.put("email", email != null ? email : "");           // puts email into response map or empty string if null
        response.put("picture", picture != null ? picture : "");     // puts picture into response map or empty string if null

        if (existingUser.isPresent()) {                              // checks if user profile is already registered in db
            response.put("username", existingUser.get().getUsername()); // adds saved username to response map
            response.put("isProfileComplete", true);                 // flags profile as complete
        } else {                                                     // if user profile is new/incomplete
            response.put("username", "");                            // sets empty username in response
            response.put("isProfileComplete", false);                // flags profile as incomplete
        }

        return response;                                             // returns final user profile map to client
    }

    @PostMapping("/profile-setup")                                   // maps http post requests to set up user profile details
    public ResponseEntity<?> setupProfile(@RequestBody Map<String, String> payload, @AuthenticationPrincipal OAuth2User principal) { // method to register or update profile username and password
        if (principal == null) {                                     // checks if user is unauthorized
            return ResponseEntity.status(401).body("Unauthorized");  // returns 401 unauthorized status if not logged in
        }

        String email = principal.getAttribute("email");              // gets email from authenticated session principal
        String username = payload.get("username");                   // extracts username from request body payload
        String password = payload.get("password");                   // extracts password from request body payload

        if (username == null || username.trim().isEmpty()) {         // validates that username is not blank
            return ResponseEntity.badRequest().body("Username is required"); // returns 400 bad request if username is missing
        }
        if (password == null || password.trim().isEmpty()) {         // validates that password is not blank
            return ResponseEntity.badRequest().body("Password is required"); // returns 400 bad request if password is missing
        }

        String cleanUsername = username.trim().startsWith("@") ? username.trim().substring(1) : username.trim(); // cleans leading @ from username if present

        User user = userRepository.findByEmail(email).orElse(new User()); // fetches existing user or creates a new user instance
        user.setEmail(email);                                        // sets user email
        user.setUsername(cleanUsername);                             // sets cleaned username
        user.setPassword(password);                                  // sets user password

        userRepository.save(user);                                   // saves or updates user record in database

        return ResponseEntity.ok("Profile saved successfully");      // returns success message with 200 ok status
    }
}