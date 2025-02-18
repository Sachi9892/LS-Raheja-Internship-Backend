package com.ls_raheja.application_form.mapper;


import com.ls_raheja.application_form.dto.degree_dto.AddressDto;
import com.ls_raheja.application_form.dto.degree_dto.AwardDto;
import com.ls_raheja.application_form.dto.degree_dto.CompetitiveExamsDto;
import com.ls_raheja.application_form.dto.degree_dto.CourseTaughtDto;
import com.ls_raheja.application_form.dto.degree_dto.PersonalInfoDto;
import com.ls_raheja.application_form.dto.degree_dto.PhdDto;
import com.ls_raheja.application_form.dto.degree_dto.QualificationDto;
import com.ls_raheja.application_form.dto.degree_dto.ResearchPaperDto;
import com.ls_raheja.application_form.dto.degree_dto.WorkExperienceDetailDto;
import com.ls_raheja.application_form.entity.degree.Address;
import com.ls_raheja.application_form.entity.degree.Award;
import com.ls_raheja.application_form.entity.degree.CompetitiveExams;
import com.ls_raheja.application_form.entity.degree.CourseTaught;
import com.ls_raheja.application_form.entity.degree.PersonalInfo;
import com.ls_raheja.application_form.entity.degree.Phd;
import com.ls_raheja.application_form.entity.degree.Qualifications;
import com.ls_raheja.application_form.entity.degree.ResearchPublication;
import com.ls_raheja.application_form.entity.degree.WorkExperience;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {


    // Map nested DTOs and entities automatically
    PersonalInfo toEntity(PersonalInfoDto personalInfoDTO);
    Address toEntity(AddressDto addressDTO);
    Qualifications toEntity(QualificationDto qualificationsDTO);
    WorkExperience toEntity(WorkExperienceDetailDto workExperienceDTO);
    Phd toEntity(PhdDto phdDTO);
    CompetitiveExams toEntity(CompetitiveExamsDto competitiveExamsDto);
    Award toEntity(AwardDto awardDto);
    CourseTaught toEntity(CourseTaughtDto dto);
    ResearchPublication toEntity(ResearchPaperDto researchPaperDto);
    

}
