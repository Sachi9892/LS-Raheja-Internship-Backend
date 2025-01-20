package com.ls_raheja.application_form.dto;

import com.ls_raheja.application_form.entity.Gender;
import com.ls_raheja.application_form.entity.JobRoles;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonalInfoDto {

    private JobRoles role;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;

    private LocalDate dob;

    private Gender gender;
}
