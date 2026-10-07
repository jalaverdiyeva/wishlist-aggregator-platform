package com.wishlist.platform.client;                               // package path configuration

import org.springframework.cloud.openfeign.FeignClient;                // import feignclient annotation for http clients
import org.springframework.web.bind.annotation.GetMapping;             // import getmapping for http get requests
import org.springframework.web.bind.annotation.PathVariable;         // import pathvariable for url path parameters
import com.wishlist.platform.dto.ExternalProductDto;                 // import dto class for external product data response

@FeignClient(name = "product-service", url = "${product.service.url:https://api.example.com}") // declares this interface as a feign client to call external apis
public interface ProductClient {                                    // interface declaration for declarative rest client

    @GetMapping("/products/{id}")                                    // maps get requests to the external product endpoint with an id path
    ExternalProductDto getProductById(@PathVariable("id") Long id);  // method signature that fetches external product data by its id
}