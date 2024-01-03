package cd.presenceless.identityservice.response;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IdentityResponse {
    private String message;
    private String status;
    private Object data;
    private String error;
    private Date date;
    private String path;
    private String method;
    private String service;
}
