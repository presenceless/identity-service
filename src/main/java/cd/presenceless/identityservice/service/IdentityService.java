package cd.presenceless.identityservice.service;

import cd.presenceless.identityservice.data.DataTransfer;
import cd.presenceless.identityservice.repository.AddressRepository;
import cd.presenceless.identityservice.repository.CitizenRepository;
import cd.presenceless.identityservice.repository.FingerPrintsRepository;
import cd.presenceless.identityservice.repository.PhotographRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {

    private final CitizenRepository citizenRepository;
    private final AddressRepository addressRepository;
    private final PhotographRepository photographRepository;
    private final FingerPrintsRepository fingerPrintsRepository;

    public IdentityService(CitizenRepository citizenRepository, AddressRepository addressRepository, PhotographRepository photographRepository, FingerPrintsRepository fingerPrintsRepository) {
        this.citizenRepository = citizenRepository;
        this.addressRepository = addressRepository;
        this.photographRepository = photographRepository;
        this.fingerPrintsRepository = fingerPrintsRepository;
    }

    @RabbitListener(queues = "${rabbitmq.enrollment.queue.identity-service}")
    public void saveIdentity(DataTransfer identity) {
        try {
            final var citizen = citizenRepository.save(identity.getCitizen());

            final var address = identity.getAddress();
            address.setCitizen(citizen);
            addressRepository.save(address);

            final var photograph = identity.getPhotograph();
            photograph.setCitizen(citizen);
            photographRepository.save(photograph);

            final var fingerPrints = identity.getFingerPrints();
            fingerPrints.forEach(fingerPrint -> fingerPrint.setCitizen(citizen));
            fingerPrintsRepository.saveAll(fingerPrints);
        } catch (Exception e) {
            System.out.println("Error saving identity: " + e.getMessage());
        }
    }
}
