package com.wishlist.platform.controller;                             // package path configuration

import org.springframework.stereotype.Controller;                    // import controller annotation for handling web view routes
import org.springframework.web.bind.annotation.GetMapping;             // import getmapping for http get request routing

@Controller                                                          // marks this class as a traditional mvc controller serving views or redirects
public class WebController {                                         // main web controller class for page routing

    @GetMapping("/")                                                 // maps http get requests on the root path
    public String redirectToLogin() {                                // method to handle root traffic redirection
        return "redirect:/login.html";                               // redirects browser straight to the login page
    }
}