package com.ls_raheja.application_form.dto.non_teaching;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NTWorkExpDetailsDTO {

    private String orgName;
    private String position;
    private String workNature;
    private Boolean isCurrentlyWorking;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String rfl; // Reason for Leaving
    private Double salary;
}
