package cd.presenceless.identityservice.repository;

import cd.presenceless.identityservice.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository
        extends JpaRepository<Citizen, Long> {
    Citizen findByPresenceLessNumber(String pNumber);
}
