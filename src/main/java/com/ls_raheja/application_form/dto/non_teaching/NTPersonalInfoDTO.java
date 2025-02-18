package com.ls_raheja.application_form.dto.non_teaching;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NTPersonalInfoDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private LocalDate dob;
    
}
