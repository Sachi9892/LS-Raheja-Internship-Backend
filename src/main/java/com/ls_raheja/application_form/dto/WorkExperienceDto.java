package com.ls_raheja.application_form.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class WorkExperienceDto {

    private boolean isFresher;
    private String organizationName;
    private String jobType; // Enum type mapped to String
    private boolean isCurrentlyWorking;
    private String jobTitle;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Double currentSalary;
    private String noticePeriod;
}
