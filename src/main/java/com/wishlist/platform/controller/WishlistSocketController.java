package com.wishlist.platform.controller;                             // package path configuration

import org.springframework.messaging.handler.annotation.MessageMapping; // import message mapping annotation for websocket destination routing
import org.springframework.messaging.handler.annotation.SendTo;      // import sendto annotation to broadcast responses to websocket topics
import org.springframework.stereotype.Controller;                    // import controller annotation for spring mvc components

@Controller                                                          // marks class as a spring controller handling websocket messages
public class WishlistSocketController {                              // main websocket controller class for real-time messaging

    @MessageMapping("/wishlist-action")                              // maps incoming websocket messages sent to /app/wishlist-action
    @SendTo("/topic/wishlist-updates")                               // broadcasts the returned message to all subscribers on /topic/wishlist-updates
    public String handleWishlistUpdate(String message) {             // method that processes incoming socket payload
        return message; // broadcasts the update to all connected clients in real-time // returns message to broadcast to all connected clients instantly
    }
}