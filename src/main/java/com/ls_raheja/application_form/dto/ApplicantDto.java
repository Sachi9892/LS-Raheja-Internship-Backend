package com.ls_raheja.application_form.dto;

import com.ls_raheja.application_form.entity.JobRoles;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicantDto {

    private JobRoles role;

    private PersonalInfoDto personalInfo;

    private AddressDto address;

    private List<QualificationDto> qualifications;

    private List<CompetitiveExamsDto> competitiveExams;

    private WorkExperienceDto workExperience;

    private PhdDto phd;

}
