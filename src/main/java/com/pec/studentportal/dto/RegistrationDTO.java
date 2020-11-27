package com.pec.studentportal.dto;

import com.pec.studentportal.validators.PasswordMatches;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class RegistrationDTO {
    @NotNull
    private String emailId;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    private String userRole;
}
