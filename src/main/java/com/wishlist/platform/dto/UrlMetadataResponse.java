package com.wishlist.platform.dto;       // package path configuration

public record UrlMetadataResponse(       // immutable java record class for transferring extracted url metadata
        String title,                    // component field storing extracted product page title
        String image,                    // component field storing extracted product image url
        String brand,                    // component field storing extracted product brand name
        String price                     // component field storing extracted product price value
) {}                                     // end of record definition (automatically generates constructor, getters, equals, hashcode, and toString)
