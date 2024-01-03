package cd.presenceless.identityservice.service;

import cd.presenceless.identityservice.entity.Citizen;

public interface IdentitySearchService {
    Citizen identify(String pNumber);
    Iterable<Citizen> getAllCitizens();
}
