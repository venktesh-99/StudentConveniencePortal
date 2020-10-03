package com.pec.studentportal.dto;

import com.pec.studentportal.enums.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    @NotNull
    private String emailId;

    @NotNull
    private String password;

    @NotNull
    private String userRole;
}
