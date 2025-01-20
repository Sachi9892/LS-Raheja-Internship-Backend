package com.ls_raheja.application_form.dto;

import java.time.LocalDate;

import com.ls_raheja.application_form.entity.NoticePeriod;

import lombok.Data;

@Data
public class WorkExperienceDetailDto {

    private String organizationName;
    private String jobTitle;
    private Boolean isCurrentlyWorking;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String currentSalary;
    private NoticePeriod noticePeriod;

}
