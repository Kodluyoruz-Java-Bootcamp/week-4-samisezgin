package emlakcepte.client;

import emlakcepte.client.model.Payment;
import emlakcepte.model.User;
import emlakcepte.repository.UserRepository;
import emlakcepte.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PaymentServiceClient {
    private final UserRepository userRepository;

    public PaymentServiceClient(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Payment processPayment(Integer userId, String cardNo,Integer extensionAmount) {
        var user = userRepository.getReferenceById(userId);

        Payment payment = new Payment(user.getId(), cardNo);

        String url = "http://localhost:9095/payment/" + cardNo+"/"+extensionAmount;
        RestTemplate template = new RestTemplate();
        HttpEntity<Payment> request = new HttpEntity<>(payment);

        return template.postForObject(url, request, Payment.class);

    }

}


