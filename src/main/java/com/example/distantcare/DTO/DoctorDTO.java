package com.example.distantcare.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DoctorDTO {

    @NotEmpty(message = "Username Require")
    @Size(min = 4,max = 15 , message = "Username length must be 4-15 characters ")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username must contain only letters and numbers")
    private String username;

    @NotEmpty(message = "Password Required")
    @Size(min = 6 , max = 20 , message = "Password length must be 6-20 characters ")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain symbols, numbers, and uppercase and lowercase characters")
    private String password;

    @NotEmpty(message = "Full name is Required")
    @Size(min = 3, max = 35 , message = "Full name length must be between 3-35 characters ")
    private String fullName;

    //@NotNull(message = "Birthday Date is Required")
    private LocalDate birthDate;

    @NotEmpty(message = "Phone Number is Required ")
    @Pattern(regexp="\\d+", message="Phone number must contain only integers")
    // @Size(max = 10 , message = "PhoneNumber must be 10 characters ")
    private String phoneNumber;

    @NotEmpty(message = "Emergency Phone Number is Required ")
    @Pattern(regexp="\\d+", message="Emergency Phone number must contain only integers")
    @Size(max = 10 , message = "PhoneNumber must be 10 characters ")
    private String emergencyPhoneNumber;

    @NotNull(message = "Age is Required")
    private int age;

    @Email(message = "Email must be valid format ")
    // @Size(max = 30 , message = "Email length max 30 characters ")
    private String email;

    @NotEmpty(message = "gender is Required")
    @Pattern(regexp = "^(male|female)$", message = "Gender must be either Male or Female")
    private String gender;

    @NotEmpty(message = "Address is Required")
    private String address;

    @Pattern(regexp = "^(patient|DOCTOR|admin)$", message = "Role must be either Patient , Doctor or Admin")
    @NotEmpty(message = "Role is Required")
    private String role;
    @NotEmpty(message = "city is Required")
    private String city;
    private int ratingToPatient;
    // ----------
    @NotEmpty(message = "hospital Name Major must be not empty")
    private String hospitalName;
    @NotEmpty(message = "Doctor Major must be not empty")
    private String major; // تخصص الطبيب
    @NotEmpty(message = "Degree is required")
    private String degree;
}
