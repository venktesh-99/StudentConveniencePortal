package com.pec.studentportal.validators.impl;

import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.validators.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        RegistrationDTO registrationDTO = (RegistrationDTO) obj;
        return registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword());
    }
}
