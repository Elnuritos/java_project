package ba.edu.ibu.demo.api.impl;

import ba.edu.ibu.demo.core.api.mailsender.EmailService;
import ba.edu.ibu.demo.core.model.Publisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "configuration.emailsender.default", havingValue = "api")
public class ApiEmailService implements EmailService {

    @Override
    public String send(List<Publisher> publishers, String message) {
        for (Publisher publisher : publishers) {
            System.out.println("Message sent to: " + publisher.getEmail());
        }
        return "Message: " + message + " | Sent via Mailgun.";
    }
}
