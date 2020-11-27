package com.pec.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pec.studentportal.enums.Gender;
import com.pec.studentportal.enums.Relation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRelatives extends AbstractEntity<Integer> {

    private String name;

    private Gender gender;

    private Relation relation;

    private String contactNumbers;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="student_id")
    private Student student;

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = String.join(",", contactNumbers);
    }

    public List<String> getContactNumbers() {
        return Arrays.asList(this.contactNumbers.split(",", -1));
    }

    public StudentRelatives(String name, Gender gender, Relation relation,List<String> contactNumbers) {
        this.name = name;
        this.gender = gender;
        this.relation = relation;
        setContactNumbers(contactNumbers);
    }
}
