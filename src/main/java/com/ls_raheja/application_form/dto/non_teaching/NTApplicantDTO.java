package com.ls_raheja.application_form.dto.non_teaching;

import java.util.List;

import lombok.Data;

@Data
public class NTApplicantDTO {
    
    private NTPersonalInfoDTO personalInfo;
    private List<NTQualificationInfoDTO> qualificationInfo;
    private NTAdditionalInfoDTO addInfo;
    private List<NTWorkExpDetailsDTO> workExp;
    
}
