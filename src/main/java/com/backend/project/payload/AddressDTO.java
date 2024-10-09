package com.backend.project.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    @NotBlank(message = "Center Address cannot be blank.")
    private String detailedAddress;
    @NotBlank(message = "City Cannot be blank.")
    private String city;
    @NotBlank(message = "State Cannot be blank.")
    private String state;
    @NotBlank(message = "Pin Code Cannot be blank.")
    private int pinCode;
}
