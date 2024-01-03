package cd.presenceless.identityservice.service;

import cd.presenceless.identityservice.entity.Citizen;
import cd.presenceless.identityservice.repository.CitizenRepository;
import org.springframework.stereotype.Service;

@Service
public class IdentitySearchServiceImpl implements IdentitySearchService {

    private final CitizenRepository citizenRepository;

    public IdentitySearchServiceImpl(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    /**
     * @param pNumber - personal number
     * @return Citizen
     */
    @Override
    public Citizen identify(String pNumber) {
        return citizenRepository.findByPresenceLessNumber(pNumber);
    }

    /**
     * @return Iterable<Citizen>
     */
    @Override
    public Iterable<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }
}
