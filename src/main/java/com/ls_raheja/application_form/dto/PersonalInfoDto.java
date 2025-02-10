package com.ls_raheja.application_form.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private JobRoles role;

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Gender gender;
}
