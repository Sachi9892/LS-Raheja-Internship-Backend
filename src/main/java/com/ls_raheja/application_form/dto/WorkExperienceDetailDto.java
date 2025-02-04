package com.ls_raheja.application_form.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls_raheja.application_form.entity.NoticePeriod;

import lombok.Data;

@Data
public class WorkExperienceDetailDto {

    private String organizationName;
    private String jobTitle;
    private Boolean isCurrentlyWorking;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    private String currentSalary;
    
    private NoticePeriod noticePeriod;

}
