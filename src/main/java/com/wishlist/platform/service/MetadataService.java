package com.wishlist.platform.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class MetadataService {

    // protect this method with the circuit breaker configuration defined above
    @CircuitBreaker(name = "wishlistService", fallbackMethod = "fallbackScrapeMetadata")
    public String scrapeUrlMetadata(String url) {
        // your actual JSoup scraping logic here...
        // if this fails or takes too long, it will automatically trigger the fallback
        return "Scraped Metadata Result";
    }

    // fallback method must have the same return type plus an optional throwable parameter
    public String fallbackScrapeMetadata(String url, Throwable t) {
        return "Fallback: Metadata service is currently unavailable. Using default preview.";
    }
}