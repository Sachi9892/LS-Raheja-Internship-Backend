package com.ls_raheja.application_form.mapper;

import org.mapstruct.Mapper;

import com.ls_raheja.application_form.dto.non_teaching.NTAdditionalInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTApplicantDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTPersonalInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTQualificationInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTWorkExpDetailsDTO;
import com.ls_raheja.application_form.entity.non_teaching.NTAddtionalInfo;
import com.ls_raheja.application_form.entity.non_teaching.NTPersonalInfo;
import com.ls_raheja.application_form.entity.non_teaching.NTQualificationInfo;
import com.ls_raheja.application_form.entity.non_teaching.NTWorkExpDetails;
import com.ls_raheja.application_form.entity.non_teaching.NonTeachingApplicant;

@Mapper(componentModel = "spring")
public interface NonTeachingMapper {
    
     // Map nested DTOs and entities automatically
    NonTeachingApplicant toEntity(NTApplicantDTO dto);
    NTPersonalInfo toEntity(NTPersonalInfoDTO dto);
    NTQualificationInfo toEntity(NTQualificationInfoDTO dto);
    NTWorkExpDetails toEntity(NTWorkExpDetailsDTO dto);
    NTAddtionalInfo toEntity(NTAdditionalInfoDTO dto);


    //For response return dto
    NTApplicantDTO toDto(NonTeachingApplicant applicant);


}
