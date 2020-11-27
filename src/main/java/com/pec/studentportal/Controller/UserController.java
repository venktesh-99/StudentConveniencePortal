package com.pec.studentportal.Controller;

import com.pec.studentportal.Services.UserService;
import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.response.GenericApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/register/user")
    public GenericApiResponse registerUser(@RequestBody RegistrationDTO registrationDTO) {
        try {
            GenericApiResponse response = userService.registerUser(registrationDTO);
            log.info("register_user_success: Registration successful for user:{}",registrationDTO.getEmailId());
            return response;
        } catch (Exception e) {
            log.error("register_user_failure: Registration failure for user:{} with error:{}",registrationDTO.getEmailId(),e);
            return new GenericApiResponse(false,"Some error occurred.Kindly Contact Admin.");
        }
    }
}
