# 🏨 Hotel Booking & Management System

A backend-focused hotel booking and management application built with Java and Spring Boot. The project provides secure authentication, hotel and room management, booking and inventory handling, dynamic pricing, and Stripe payment integration.

## 🚀 Features

- 🔐 Authentication and authorization using Spring Security and JWT
- 🏨 Hotel management
- 🛏️ Room management
- 📅 Hotel and room availability checking
- 📋 Booking and reservation management
- 👤 User profile and booking history
- 💳 Stripe Payment Gateway integration
- 🔔 Stripe webhook handling for payment events
- 🔒 Pessimistic locking to help prevent overlapping bookings
- 💰 Dynamic pricing using the Strategy Design Pattern
- 📈 Occupancy-based pricing
- 🎉 Holiday-based pricing
- ⚡ Surge and urgency-based pricing
- 🛡️ Global exception handling
- 📦 DTO-based API architecture
- 🌐 RESTful APIs

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend development |
| Spring Boot | Application framework |
| Spring Security | Authentication and authorization |
| JWT | Token-based authentication |
| Spring Data JPA | Database access |
| PostgreSQL | Relational database |
| Stripe API | Online payments |
| Maven | Build and dependency management |
| REST API | Client-server communication |

## 🏗️ Architecture

The project follows a layered Spring Boot architecture:

```text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
PostgreSQL
```

### Main Layers

- **Controller** – Handles HTTP requests and responses
- **Service** – Contains business logic
- **Repository** – Handles persistence and database queries
- **Entity** – Represents database models
- **DTO** – Transfers data between application layers
- **Security** – Handles JWT authentication and authorization
- **Strategy** – Implements dynamic pricing rules

## 💰 Dynamic Pricing

Dynamic pricing is implemented using the **Strategy Design Pattern**, making pricing rules easier to extend and maintain.

The project includes strategies for:

- Base pricing
- Occupancy-based pricing
- Holiday pricing
- Surge pricing
- Urgency-based pricing

## 💳 Payment Flow

Stripe is used for payment processing.

```text
User
 ↓
Create Booking
 ↓
Calculate Final Price
 ↓
Create Stripe Payment
 ↓
Payment Completed
 ↓
Stripe Webhook
 ↓
Backend Updates Payment Status
 ↓
Booking Confirmed
```

## 🔒 Booking Concurrency

The booking flow uses **pessimistic locking** for inventory-related operations to reduce the risk of conflicting bookings when multiple users try to reserve the same inventory.

## 📂 Project Structure

```text
src/
├── main/
│   ├── java/com/stayease/hotel_booking_management/
│   │   ├── Controller/
│   │   ├── advice/
│   │   ├── config/
│   │   ├── dto/
│   │   ├── entity/
│   │   ├── exception/
│   │   ├── repository/
│   │   ├── security/
│   │   ├── service/
│   │   ├── strategy/
│   │   └── util/
│   └── resources/
└── test/
```

## 🔐 Security

Sensitive configuration such as API keys, passwords, JWT secrets, database credentials, and Stripe webhook secrets should not be committed to GitHub.

## 👨‍💻 Author

**Soumyaranjan Jena**

B.Tech – Computer Science and Technology  
Nalanda Institute of Technology, Bhubaneswar

- LinkedIn: https://www.linkedin.com/in/soumyaranjan-jena-16b930321
- GitHub: https://github.com/soumya7327
