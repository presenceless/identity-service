package cd.presenceless.identityservice.repository;

import cd.presenceless.identityservice.entity.Address;
import cd.presenceless.identityservice.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository
        extends JpaRepository<Address, Long> {
    Address findByCitizen(Citizen citizen);
}
