package com.wishlist.platform.repository;                           // package path configuration

import com.wishlist.platform.entity.Product;                         // import product entity class
import org.springframework.data.jpa.repository.JpaRepository;        // import jpa repository interface for standard database crud operations
import org.springframework.stereotype.Repository;                    // import repository annotation to mark class as a data access bean

@Repository                                                          // tells spring this interface is a database repository component
public interface ProductRepository extends JpaRepository<Product, Long> { // repository interface providing crud and paging methods for product entities
}