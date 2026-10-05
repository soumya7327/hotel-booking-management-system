package com.stayease.hotel_booking_management.entity;



import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable

public class HotelContactInfo {

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200,
            message = "Address must be between 5 and 200 characters")
    private String address;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^\\+[1-9][0-9]{0,3}$", message = "Invalid country code")
    private String countryCode;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must contain 10 digits")
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String location;
}
