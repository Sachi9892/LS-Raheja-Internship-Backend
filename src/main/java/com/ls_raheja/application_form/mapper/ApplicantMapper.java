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
    WorkExperience toEntity(WorkExperienceDto workExperienceDTO);
    Phd toEntity(PhdDto phdDTO);

    PersonalInfoDto toDto(PersonalInfo personalInfo);
    AddressDto toDto(Address address);
    QualificationDto toDto(Qualifications qualifications);
    WorkExperienceDto toDto(WorkExperience workExperience);
    PhdDto toDto(Phd phd);

}
