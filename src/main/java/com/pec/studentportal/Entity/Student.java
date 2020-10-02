package com.pec.studentportal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(nullable = false)
    private Integer studentId;

    @Column(nullable = false)
    private String email;

    private String firstName;

    private String middleName;

    private String lastName;

}
