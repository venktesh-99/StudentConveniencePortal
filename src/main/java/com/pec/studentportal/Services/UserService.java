package com.pec.studentportal.Services;

import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.response.GenericApiResponse;

public interface UserService {

    GenericApiResponse registerUser(RegistrationDTO registrationDTO);

}
