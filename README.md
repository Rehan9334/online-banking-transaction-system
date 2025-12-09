# 💳 Online Banking & Transaction System | Microservices + Kafka + Redis + JWT

A secure and scalable Online Banking System built using Java & Spring Boot with microservices architecture. 
This project enables user account management, secure authentication, fund transfer, transaction history 
and async transaction processing using Kafka.

---

## 🚀 Key Features

✔ Microservices architecture — Independent banking modules  
✔ JWT-based Authentication & Role-Based Access Control (RBAC)  
✔ Kafka messaging for asynchronous transaction processing  
✔ Redis caching for improved performance  
✔ MySQL as primary data storage  
✔ Docker-enabled services for seamless deployment  
✔ Clear layered architecture for clean maintainable code  

---

## 🛠 Tech Stack

| Category | Technologies |
|---------|--------------|
| Backend | Java 8+, Spring Boot, Spring Security, JPA/Hibernate |
| Messaging | Apache Kafka |
| Cache | Redis |
| Database | MySQL |
| Tools | Docker, Postman, Git, IntelliJ IDEA |
| Docs | Swagger UI / OpenAPI |

---

## 🔐 Microservices Included

| Service | Responsibility |
|--------|----------------|
| Auth Service | Login, signup, JWT generation, RBAC |
| Account Service | Account creation, balance checks |
| Transaction Service | Fund transfers, transaction history |
| Notification Service | Async notifications (via Kafka topics) |

---

## ⚙️ Architecture Diagram

📌 *Diagram Placeholder*  
(I will generate a clean diagram for you — Microservices + Kafka + Redis flow)

---

## 🧪 API Documentation

Swagger UI enabled:  

Endpoints include:
- `POST /auth/login`
- `POST /transactions/transfer`
- `GET /transactions/history`

(Full API listing will be added with screenshot)

---

## 🧩 How to Run (Local)

```sh
# Start database & Kafka/Redis containers (if using Docker Compose)
docker-compose up -d

# Run each microservice individually
mvn clean install
mvn spring-boot:run
