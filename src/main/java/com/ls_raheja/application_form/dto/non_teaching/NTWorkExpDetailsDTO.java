package com.ls_raheja.application_form.dto.non_teaching;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NTWorkExpDetailsDTO {

    private Boolean isCurrentlyWorking;

    private String orgName;
    private String position;
    private String workNature;

    private String fromDate;
    private String toDate;
    private String rfl; // Reason for Leaving
    private Double salary;
}
