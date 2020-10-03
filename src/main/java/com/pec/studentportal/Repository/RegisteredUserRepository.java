package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Integer> {

    public RegisteredUser findByEmailId(String emailId);

}
