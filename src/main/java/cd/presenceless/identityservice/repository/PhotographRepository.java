package cd.presenceless.identityservice.repository;

import cd.presenceless.identityservice.entity.Photograph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotographRepository
        extends JpaRepository<Photograph, Long> { }
