package cd.presenceless.identityservice.repository;

import cd.presenceless.identityservice.entity.FingerPrints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FingerPrintsRepository
        extends JpaRepository<FingerPrints, Long> { }
