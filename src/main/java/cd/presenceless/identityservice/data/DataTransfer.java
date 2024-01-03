package cd.presenceless.identityservice.data;

import cd.presenceless.identityservice.entity.Address;
import cd.presenceless.identityservice.entity.Citizen;
import cd.presenceless.identityservice.entity.FingerPrints;
import cd.presenceless.identityservice.entity.Photograph;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataTransfer {
    Citizen citizen;
    Address address;
    Photograph photograph;
    List<FingerPrints> fingerPrints;
}
