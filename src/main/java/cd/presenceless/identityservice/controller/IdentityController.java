package cd.presenceless.identityservice.controller;

import cd.presenceless.identityservice.entity.Citizen;
import cd.presenceless.identityservice.request.IdentityReq;
import cd.presenceless.identityservice.response.IdentityResponse;
import cd.presenceless.identityservice.service.IdentitySearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/identity/")
public class IdentityController {
    private final IdentitySearchService identitySearchService;

    public IdentityController(IdentitySearchService identitySearchService) {
        this.identitySearchService = identitySearchService;
    }

    @PostMapping
    public ResponseEntity<IdentityResponse> identify(
            @RequestBody IdentityReq identity,
            @RequestParam(value = "address", required = false) boolean address,
            @RequestParam(value = "photograph", required = false) boolean photograph,
            @RequestParam(value = "mobileNumber", required = false) boolean mobileNumber,
            @RequestParam(value = "email", required = false) boolean email
    ) {
        try {
            final var citizen = identitySearchService.identify(identity.getPresenceLessNumber());
            Citizen c = getCitizen(address, photograph, mobileNumber, email, citizen);

            IdentityResponse response = IdentityResponse.builder()
                    .message("Citizen identified")
                    .status("success")
                    .data(c)
                    .date(new Date())
                    .path("/api/v1/identity/")
                    .service("identity-service")
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            IdentityResponse response = IdentityResponse.builder()
                    .message("Citizen not identified")
                    .status("error")
                    .error(e.getMessage())
                    .date(new Date())
                    .path("/api/v1/identity/")
                    .service("identity-service")
                    .build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Citizen>> all() {
        try {
            final var citizens = identitySearchService.getAllCitizens();
            return ResponseEntity.ok(citizens);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Citizen getCitizen(
            boolean address, boolean photograph, boolean mobileNumber, boolean email, Citizen citizen) {
        Citizen c = Citizen.builder()
                .name(citizen.getName())
                .dateOfBirth(citizen.getDateOfBirth())
                .gender(citizen.getGender())
                .presenceLessNumber(citizen.getPresenceLessNumber())
                .build();

        if (address) {
            c.setAddress(citizen.getAddress());
        }

        if (photograph) {
            c.setPhotograph(citizen.getPhotograph());
        }

        if (mobileNumber) {
            c.setMobileNumber(citizen.getMobileNumber());
        }

        if (email) {
            c.setEmail(citizen.getEmail());
        }
        return c;
    }
}
