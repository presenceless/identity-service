package cd.presenceless.identityservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/identity/")
public class IdentityController {

    @PostMapping
    public Object identify(@RequestBody Object identity) {
        return identity;
    }
}
