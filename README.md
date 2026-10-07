# 🛒 Wishlist Aggregator Platform

An enterprise-grade, full-stack wishlist aggregation and social vibe-sharing platform built with **Spring Boot** and an interactive multi-page modern frontend. Designed to unify scattered user wishlists, track cross-platform products, and provide real-time community engagement analytics.

---

## 🚀 Key Features

* **Cross-Platform Wishlist Aggregation**: Unified dashboard to manage items from various external sources using robust REST clients and scraping utilities.
* **Real-Time Analytics & WebSockets**: Live event streaming powered by Spring WebSocket (STOMP/SockJS) for real-time user activity tracking and notification alerts.
* **Interactive Frontend Suite**: A 6-page responsive web application featuring custom landing pages, user authentication workflows, user profiles, favorites management, interactive moodboards, and live analytics dashboards.
* **Secure Architecture**: Spring Security integration with OAuth2 support and secure session management.

---

## 🛠️ Tech Stack

* **Backend**: Java 17+, Spring Boot, Spring Data JPA, Spring Security, Spring WebSockets (STOMP), Spring Cloud OpenFeign
* **Database**: H2 / MySQL / PostgreSQL (configurable via application properties)
* **Frontend**: HTML5, CSS3, JavaScript (SockJS & STOMP client libraries)
* **Containerization**: Docker & Docker Compose support

---

## 📂 Project Structure

```text
wishlist-aggregator-platform/
├── src/main/java/com/wishlist/platform/
│   ├── client/         # OpenFeign external API clients
│   ├── config/         # Security and WebSocket configurations
│   ├── controller/     # REST and Web controllers
│   ├── dto/            # Data Transfer Objects
│   ├── entity/         # JPA Entities (User, Product, WishlistItem)
│   ├── repository/     # Spring Data Repositories
│   └── service/        # Core business logic & scrapers
└── src/main/resources/
    ├── static/         # Frontend HTML pages & assets (analytics, favorites, login, etc.)
    └── application.properties
