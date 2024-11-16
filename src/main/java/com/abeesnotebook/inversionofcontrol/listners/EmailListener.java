package com.abeesnotebook.inversionofcontrol.listners;

import com.abeesnotebook.inversionofcontrol.event.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @EventListener
    public void onApplicationEvent(UserRegisteredEvent event) {
        // Send welcome email logic
        System.out.println("Sending welcome email to: " + event.getUser().email());
    }
}