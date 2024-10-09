package com.backend.project.payload;


import com.backend.project.entities.Address;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CenterDTO {
    private String id;
    @NotBlank(message = "Center name cannot be empty or blank.")
    @Size(min = 4,max = 40, message = "Center Name should be less than 40 characters.")
    private String centerName;
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center Code must be of 12 characters with only alphanumeric value.")
    private String centerCode;
    private AddressDTO address;
    private int studentCapacity;
    private List<CourseDTO> courses;
    private LocalDateTime createdAt;
    @Email(message = "PLease enter a valid email. For example example@gmail.com.")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "Phone number should be of 10 digits.")
    @NotBlank(message = "Phone number can't be blank")
    private String phone;
}
