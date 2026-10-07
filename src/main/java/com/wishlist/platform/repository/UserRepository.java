package com.wishlist.platform.repository;                           // package path configuration

import com.wishlist.platform.entity.User;                            // import user entity class
import org.springframework.data.jpa.repository.JpaRepository;        // import jpa repository interface for standard database operations
import java.util.Optional;                                           // import optional wrapper to handle nullable query results safely

public interface UserRepository extends JpaRepository<User, Long> {  // repository interface providing data access methods for user entities[cite: 15]
    Optional<User> findByEmail(String email);                        // query method to find a user by their email address[cite: 15]
    Optional<User> findByUsername(String username);                  // query method to find a user by their username handle[cite: 15]
}