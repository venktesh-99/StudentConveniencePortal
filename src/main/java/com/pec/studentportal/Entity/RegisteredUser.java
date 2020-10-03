package com.pec.studentportal.Entity;

import com.pec.studentportal.enums.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisteredUser extends AbstractEntity<Integer>{

    @NotNull
    private String emailId;

    @NotNull
    private String password;

    @NotNull
    private Role userRole;
}
