package com.wishlist.platform.config;                               // package path configuration

import org.springframework.context.annotation.Bean;                  // import bean annotation to manage objects in spring container
import org.springframework.context.annotation.Configuration;         // import configuration annotation for config classes
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // import httpsecurity for security rule settings
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; // import enabだけでなくewebsecurity to turn on spring security
import org.springframework.security.web.SecurityFilterChain;           // import securityfilterchain interface for security filter rules

@Configuration                                                       // tells spring this class contains configuration settings
@EnableWebSecurity                                                   // enables spring security web features in the application
public class SecurityConfig {                                        // main security configuration class

    @Bean                                                            // registers the returned filter chain as a spring bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // defines security rules for http requests
        http
                .csrf(csrf -> csrf.disable()) // disabled for simplicity in demo rest calls[cite: 5]
                .authorizeHttpRequests(auth -> auth                      // configures url access permissions
                        // added /index.html and /favorites.html to permitall so guest/apple navigation works instantly[cite: 5]
                        .requestMatchers(                                // specifies public urls that do not need login
                                "/",
                                "/index.html",
                                "/favorites.html",
                                "/login.html",
                                "/profile.html",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/cleanapple.png"
                        ).permitAll()                                    // allows everyone to access these paths without authentication[cite: 5]
                        .anyRequest().authenticated()                    // requires login for any other endpoint[cite: 5]
                )
                .oauth2Login(oauth2 -> oauth2                            // configures google oauth2 login
                        // custom landing page for unauthenticated users[cite: 5]
                        .loginPage("/login.html")                        // sets custom login page route[cite: 5]
                        // redirect straight to the wishlist board upon successful login[cite: 5]
                        .defaultSuccessUrl("/index.html", true)          // redirects here after successful google login[cite: 5]
                )
                .logout(logout -> logout                                 // configures logout behavior
                        .logoutSuccessUrl("/login.html")                 // redirects here after logging out[cite: 5]
                        .permitAll()                                     // allows everyone to logout[cite: 5]
                );

        return http.build();                                             // builds and returns the security filter chain[cite: 5]
    }
}