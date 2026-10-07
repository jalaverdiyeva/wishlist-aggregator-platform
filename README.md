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



## 📸 Frontend Preview & UI Showcase

<div align="center">
  <h3>✨ Login & Authentication Experience</h3>
  <p>Manifesting, not over-consuming — curate with intention[cite: 4].</p>
  <img src="docs/screenshots/login.png" alt="Login Page" width="700"/>
</div>

<div align="center">
  <h3>🎨 Main Wishlist Board & Vibe Panel</h3>
  <img src="docs/screenshots/dashboard.png" alt="Dashboard" width="700"/>
  <img src="docs/screenshots/dashboard2.png" alt="Dashboard Detailed View" width="700"/>
  <img src="docs/screenshots/dashboard3.png" alt="Dashboard Collections View" width="700"/>
</div>

<div align="center">
  <h3>🔮 Creative Vibe & Moodboard Sections</h3>
  <img src="docs/screenshots/creative-vibe.png" alt="Creative Vibe" width="700"/>
  <img src="docs/screenshots/creative-vibe2.png" alt="Creative Vibe Alternate" width="700"/>
</div>

<div align="center">
  <h3>⚙️ Personal Center & Profile Settings</h3>
  <img src="docs/screenshots/profile-settings.png" alt="Profile Settings" width="700"/>
</div>

<div align="center">
  <h3>📊 Demographics & Lifestyle Matrix</h3>
  <img src="docs/screenshots/demographics-matrix.png" alt="Demographics Matrix" width="700"/>
</div>

<div align="center">
  <h3>❤️ Saved Favorites</h3>
  <img src="docs/screenshots/saved-favorites.png" alt="Saved Favorites" width="700"/>
</div>

<div align="center">
  <h3>📈 Mindful Analytics Overview</h3>
  <img src="docs/screenshots/analytics-overview.png" alt="Analytics Overview" width="700"/>
</div>

<div align="center">
  <h3>🔮 Situational Vibe Check & Strategic Planning</h3>
  <img src="docs/screenshots/analytics-vibe-check.png" alt="Analytics Vibe Check" width="700"/>
  <img src="docs/screenshots/decision-matrix.png" alt="Decision Matrix" width="700"/>
</div>
