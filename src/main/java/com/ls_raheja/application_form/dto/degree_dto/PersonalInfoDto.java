package com.ls_raheja.application_form.dto.degree_dto;


import com.ls_raheja.application_form.entity.degree.Gender;
import com.ls_raheja.application_form.entity.degree.JobRoles;
import com.ls_raheja.application_form.entity.degree.MaritalStatus;


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
    
    private AddressDto addressDto;
    
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer noOfChilds;
    private String caste;
    private Long aadhar;
    private String pan;
    private String passport;

}
