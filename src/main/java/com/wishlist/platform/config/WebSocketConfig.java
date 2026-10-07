package com.wishlist.platform.config;                               // package path configuration

import org.springframework.context.annotation.Configuration;         // import configuration annotation for config classes
import org.springframework.messaging.simp.config.MessageBrokerRegistry; // import message broker registry for STOMP destinations
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker; // import enablewebsocketmessagebroker to turn on websocket message handling
import org.springframework.web.socket.config.annotation.StompEndpointRegistry; // import stomp endpoint registry to register websocket connection endpoints
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer; // import interface to configure message broker options

@Configuration                                                       // tells spring this class contains configuration settings
@EnableWebSocketMessageBroker                                        // enables websocket message broker functionality
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // class implementing websocket configuration methods

    @Override                                                        // overrides the method to register stomp endpoints
    public void registerStompEndpoints(StompEndpointRegistry registry) { // registers websocket connection endpoints
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); // adds /ws endpoint with wildcard origins and sockjs fallback support
    }

    @Override                                                        // overrides the method to configure message broker routes
    public void configureMessageBroker(MessageBrokerRegistry registry) { // configures prefix routing for client messages and brokers
        registry.setApplicationDestinationPrefixes("/app");          // sets prefix for messages routed to @MessageMapping methods
        registry.enableSimpleBroker("/topic");                       // enables simple in-memory broker for destination topics starting with /topic
    }
}