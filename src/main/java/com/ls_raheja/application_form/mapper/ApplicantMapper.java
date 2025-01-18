package com.ls_raheja.application_form.mapper;

import com.ls_raheja.application_form.dto.*;
import com.ls_raheja.application_form.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {

    // Map from DTO to Entity
    @Mapping(source = "role", target = "role", qualifiedByName = "stringToJobRole")
    Applicant toEntity(ApplicantDto applicantDTO);

    // Map from Entity to DTO
    @Mapping(source = "role", target = "role", qualifiedByName = "jobRoleToString")
    ApplicantDto toDto(Applicant applicant);

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

    // Custom mappings for JobRoles enum
    @Named("stringToJobRole")
    default JobRoles stringToJobRole(String role) {
        return JobRoles.valueOf(role);
    }

    @Named("jobRoleToString")
    default String jobRoleToString(JobRoles role) {
        return role.name();
    };
}
