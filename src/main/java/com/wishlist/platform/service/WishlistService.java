package com.wishlist.platform.service;

import com.wishlist.platform.entity.WishlistItem;
import com.wishlist.platform.repository.WishlistItemRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistItemRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    public WishlistService(WishlistItemRepository repository, SimpMessagingTemplate messagingTemplate) {
        this.repository = repository;
        this.messagingTemplate = messagingTemplate;
    }

    // Cache the wishlist results mapped by user email
    @Cacheable(value = "wishlists", key = "#userEmail")
    public List<WishlistItem> getUserWishlist(String userEmail) {
        return repository.findByUserEmail(userEmail);
    }

    // Clear the user's cached wishlist when a new item is added and broadcast live update
    @CacheEvict(value = "wishlists", key = "#userEmail")
    public WishlistItem addItem(WishlistItem item, String userEmail) {
        item.setUserEmail(userEmail);
        WishlistItem savedItem = repository.save(item);

        // Broadcast real-time update to all connected WebSocket clients
        messagingTemplate.convertAndSend("/topic/wishlist-updates", "Added: " + savedItem.getName());

        return savedItem;
    }

    // Clear the user's cached wishlist when an item is deleted and broadcast live update
    @CacheEvict(value = "wishlists", key = "#userEmail")
    public void deleteItem(Long id, String userEmail) {
        WishlistItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized: You do not own this item");
        }

        repository.deleteById(id);

        // Broadcast real-time update to all connected WebSocket clients
        messagingTemplate.convertAndSend("/topic/wishlist-updates", "Deleted item ID: " + id);
    }
}