# Inversion of Control (IoC) in Spring Boot

> Companion code for: [Inversion of Control: Simplifying Software Design for Developers](https://abeejavajournal.com/blog/post/inversion-of-control:-simplifying-software-design-for-developers)

## Overview

This project demonstrates **Inversion of Control (IoC)** and **Event-Driven Architecture** in Spring Boot through a practical user registration system. It shows how Spring manages dependencies and object lifecycles for loosely coupled, testable code.

## What is Inversion of Control?

### Traditional Approach (Without IoC)
```java
public class UserRegistrationService {
    private EmailSender emailSender;

    public UserRegistrationService() {
        this.emailSender = new EmailSender();  // You create dependencies
    }
}
```
**Problems:** Tight coupling, hard to test, inflexible

### With IoC
```java
@Service
public class UserRegistrationService {
    private final ApplicationEventPublisher eventPublisher;

    // Spring injects the dependency
    public UserRegistrationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
}
```
**Benefits:** Loose coupling, easy to test, flexible, Spring manages lifecycle

## Project Structure

```
src/main/java/com/abeesnotebook/inversionofcontrol/
├── InversionOfControlApplication.java    # Main application
├── bootstrap/CommandLineApp.java         # Triggers demo on startup
├── domain/User.java                      # User record
├── event/UserRegisteredEvent.java        # Custom event
├── listners/
│   ├── EmailListener.java                # Sends welcome email
│   └── LoggingListener.java              # Logs registration
└── service/UserRegistrationService.java  # Publishes events
```

## How It Works

```
1. Spring Boot starts → IoC container creates all beans
2. CommandLineApp runs → creates User and calls service
3. UserRegistrationService → publishes UserRegisteredEvent
4. Spring notifies listeners → EmailListener + LoggingListener respond
```

### Key Components

**Service (Event Publisher):**
```java
@Service
public class UserRegistrationService {
    private final ApplicationEventPublisher eventPublisher;

    public UserRegistrationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(User user) {
        System.out.println("Registering user: " + user.name());
        eventPublisher.publishEvent(new UserRegisteredEvent(this, user));
    }
}
```

**Listeners (Event Subscribers):**
```java
@Component
public class EmailListener {
    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        System.out.println("Sending welcome email to: " + event.getUser().email());
    }
}
```

## Running the Application

**Prerequisites:** JDK 17+, Maven

```bash
# Run the application
./mvnw spring-boot:run

# Build
./mvnw clean package

# Run JAR
java -jar target/inversionofcontrol-0.0.1-SNAPSHOT.jar
```

**Expected Output:**
```
Registering user: abee
Logging registration for user: abee
Sending welcome email to: abee@example.com
```

## Key Concepts

### 1. Dependency Injection (DI)
- **Constructor Injection** (recommended): Dependencies injected via constructor
- Use `final` fields for immutability
- Clear and explicit dependencies

### 2. Event-Driven Architecture
- **Publisher** emits events without knowing about listeners
- **Listeners** respond independently
- Add/remove listeners without modifying the service
- Events are **synchronous** by default

### 3. Spring Annotations
- `@SpringBootApplication` - Main application entry point
- `@Service` - Business logic layer
- `@Component` - Generic Spring-managed bean
- `@EventListener` - Marks methods that handle events

## Technology Stack

- **Java**: 17
- **Spring Boot**: 3.1.1
- **Build Tool**: Maven

## Resources

- **Blog Post**: [Inversion of Control: Simplifying Software Design](https://abeejavajournal.com/blog/post/inversion-of-control:-simplifying-software-design-for-developers)
- **Spring Events**: [Official Documentation](https://docs.spring.io/spring-framework/reference/core/beans/context-introduction.html#context-functionality-events)
- **Spring Boot**: [Documentation](https://spring.io/projects/spring-boot)

## Author

**Abee** - [Abee's Java Journal](https://abeejavajournal.com)

---

*Educational demonstration project - Feel free to fork and experiment!*
