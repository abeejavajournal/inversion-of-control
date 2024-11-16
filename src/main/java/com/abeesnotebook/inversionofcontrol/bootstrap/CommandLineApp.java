package com.abeesnotebook.inversionofcontrol.bootstrap;

import com.abeesnotebook.inversionofcontrol.domain.User;
import com.abeesnotebook.inversionofcontrol.service.UserRegistrationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineApp implements CommandLineRunner {
    private final UserRegistrationService userRegistrationService;

    public CommandLineApp(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public void run(String... args) {
        User user = new User("abee", "abee@example.com");
        // trigger the event
        userRegistrationService.registerUser(user);
    }
}