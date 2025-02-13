package com.ls_raheja.application_form.mapper;

import com.ls_raheja.application_form.dto.*;
import com.ls_raheja.application_form.entity.*;
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



}
