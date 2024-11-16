package com.abeesnotebook.inversionofcontrol.listners;

import com.abeesnotebook.inversionofcontrol.event.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoggingListener {
    @EventListener
    public void onApplicationEvent(UserRegisteredEvent event) {
        // Log registration logic
        System.out.println("Logging registration for user: " + event.getUser().name());
    }
}