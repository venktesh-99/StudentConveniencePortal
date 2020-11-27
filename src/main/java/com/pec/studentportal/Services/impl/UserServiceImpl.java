package com.pec.studentportal.Services.impl;

import com.pec.studentportal.Repository.RegisteredUserRepository;
import com.pec.studentportal.Services.UserService;
import com.pec.studentportal.dto.RegistrationDTO;
import com.pec.studentportal.response.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Override
    @Transactional
    public GenericApiResponse registerUser(RegistrationDTO registrationDTO) {
        return null;
    }
}
