package ba.edu.ibu.demo.core.api.mailsender;

import ba.edu.ibu.demo.core.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmailService {

    String send(List<Publisher> publishers, String message);

}
