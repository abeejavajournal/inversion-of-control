package com.abeesnotebook.inversionofcontrol.service;

import com.abeesnotebook.inversionofcontrol.domain.User;
import com.abeesnotebook.inversionofcontrol.event.UserRegisteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    private final ApplicationEventPublisher eventPublisher;

    public UserRegistrationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(User user) {
        // Register user logic
        System.out.println("Registering user: " + user.name());
        eventPublisher.publishEvent(new UserRegisteredEvent(this, user));
    }
}