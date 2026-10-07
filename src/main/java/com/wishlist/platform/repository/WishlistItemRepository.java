package com.wishlist.platform.repository;                           // package path configuration[cite: 15]

import com.wishlist.platform.entity.WishlistItem;                    // import wishlist item entity class[cite: 15]
import org.springframework.data.jpa.repository.JpaRepository;        // import jpa repository interface for standard database crud operations[cite: 15]
import org.springframework.stereotype.Repository;                    // import repository annotation to mark class as a data access bean[cite: 15]

import java.util.List;                                               // import list collection utility[cite: 15]

@Repository                                                          // tells spring this interface is a database repository component[cite: 15]
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> { // repository interface providing crud and paging methods for wishlist item entities[cite: 15]

    // Retrieve items belonging only to the given user email           // comment describing the purpose of the custom query method[cite: 15]
    List<WishlistItem> findByUserEmail(String userEmail);            // query method to retrieve all wishlist items filtered by a specific user email[cite: 15]
}