package com.ls_raheja.application_form.dto;

import com.ls_raheja.application_form.entity.JobType;
import com.ls_raheja.application_form.entity.NoticePeriod;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkExperienceDto {

    private boolean isFresher;
    private String organizationName;

    private JobType jobType;
    private NoticePeriod noticePeriod;

    private boolean isCurrentlyWorking;
    private String jobTitle;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Double currentSalary;

}
