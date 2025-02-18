package com.ls_raheja.application_form.dto.degree_dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantDto {

    private PersonalInfoDto personalInfo;

    private AddressDto address;

    private List<QualificationDto> qualifications = new ArrayList<>();

    private List<CompetitiveExamsDto> competitiveExams = new ArrayList<>();

    private List<ResearchPaperDto>  researchPaper = new ArrayList<>();

    private WorkExperienceDto workExperience;

    private PhdDto phd;

    private AwardDto awardDto;

    private CourseTaughtDto courseDto;

    private String refrenceName;

    private Double expectedSalary;

    private String appliedForSpecialization;

    private String extraActivity;

}
