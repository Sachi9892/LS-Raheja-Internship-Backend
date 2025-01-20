package com.ls_raheja.application_form.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Entity(name = "personal_info")
@Data
@Builder
public class PersonalInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long infoId;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private JobRoles role;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "personalInfo")
    private Applicant applicant;

    private String firstName;
    private String middleName;
    private String lastName;

    private String email;
    private String phone;

    @Temporal(TemporalType.DATE)
    private LocalDate dob;
}
