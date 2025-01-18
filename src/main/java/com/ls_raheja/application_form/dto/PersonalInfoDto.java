package com.ls_raheja.application_form.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalInfoDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
}
