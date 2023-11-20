package cd.presenceless.identityservice.service;

import cd.presenceless.identityservice.data.EnrollmentData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {

    @RabbitListener(queues = "${rabbitmq.enrollment.queue.identity-service}")
    public void saveIdentity(EnrollmentData identity) {
        System.out.println("========================================");
        System.out.println("Identifying " + identity);
    }
}
