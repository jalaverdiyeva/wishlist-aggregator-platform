package com.wishlist.platform.controller;                             // package path configuration

import com.wishlist.platform.dto.UrlMetadataResponse;                // import url metadata response dto class
import com.wishlist.platform.entity.Product;                         // import product entity class
import com.wishlist.platform.repository.ProductRepository;           // import product repository interface for database operations
import com.wishlist.platform.service.ScraperService;                 // import scraper service for extracting metadata from links
import org.springframework.http.ResponseEntity;                      // import responseentity to handle http responses and status codes
import org.springframework.web.bind.annotation.*;                    // import spring mvc annotations for rest controllers

import java.util.List;                                               // import list collection utility

@RestController                                                      // marks this class as a rest api controller returning json
@RequestMapping("/api/products")                                     // sets the base url path for all endpoints in this controller
public class ProductController {                                     // main product controller class

    private final ProductRepository productRepository;               // reference to product repository for data access
    private final ScraperService scraperService;                     // reference to scraper service for parsing url metadata

    public ProductController(ProductRepository productRepository, ScraperService scraperService) { // constructor-based dependency injection
        this.productRepository = productRepository;                  // initializes product repository
        this.scraperService = scraperService;                        // initializes scraper service
    }

    @GetMapping                                                      // maps http get requests to fetch all products
    public List<Product> getAllProducts() {                          // method to get a list of all products from database
        return productRepository.findAll();                          // returns all products from repository
    }

    @GetMapping("/{id}")                                             // maps http get requests with an id path variable
    public ResponseEntity<Product> getProductById(@PathVariable Long id) { // method to fetch a single product by its id
        return productRepository.findById(id)                        // searches database for product by id
                .map(ResponseEntity::ok)                             // returns 200 ok with product if found
                .orElse(ResponseEntity.notFound().build());          // returns 404 not found if product does not exist
    }

    @GetMapping("/parse-url")                                        // maps http get requests to parse url metadata endpoint
    public ResponseEntity<UrlMetadataResponse> parseUrl(@RequestParam String url) { // method that takes a url parameter and extracts info
        return ResponseEntity.ok(scraperService.extractMetadata(url)); // calls scraper service and returns metadata response in 200 ok
    }

    @PostMapping                                                     // maps http post requests to create a new product
    public ResponseEntity<Product> addProduct(@RequestBody Product product) { // method to save a new product sent in request body
        Product savedProduct = productRepository.save(product);      // saves the product entity to the database
        return ResponseEntity.ok(savedProduct);                      // returns the saved product with 200 ok status
    }

    @DeleteMapping("/{id}")                                          // maps http delete requests with an id path variable
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) { // method to delete a product by its id
        productRepository.deleteById(id);                            // deletes the product from database by id
        return ResponseEntity.noContent().build();                   // returns 204 no content status upon successful deletion
    }
}