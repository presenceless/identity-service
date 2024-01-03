package cd.presenceless.identityservice.request;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IdentityReq {
    private String presenceLessNumber;
}
