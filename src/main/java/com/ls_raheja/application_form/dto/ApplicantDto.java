package com.ls_raheja.application_form.dto;

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

    private WorkExperienceDto workExperience;

    private PhdDto phd;

}
