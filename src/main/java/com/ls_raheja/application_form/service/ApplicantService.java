package com.ls_raheja.application_form.service;

import com.ls_raheja.application_form.dto.*;
import com.ls_raheja.application_form.entity.*;
import com.ls_raheja.application_form.mapper.ApplicantMapper;
import com.ls_raheja.application_form.repository.ApplicantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;

    public ApplicantService(ApplicantRepository applicantRepository, ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }

    public Applicant saveApplicant(ApplicantDto applicantDto) {

         // Create a new Applicant entity
        Applicant applicant = new Applicant();

        // 1. Extract and set the Role
        applicant.setRole(applicantDto.getRole());

        // 2. Extract and map Personal Info and Address
        PersonalInfoDto personalInfoDto = applicantDto.getPersonalInfo();
        AddressDto addressDto = applicantDto.getAddress();
        Address address = applicantMapper.toEntity(addressDto);
        PersonalInfo personalInfo = applicantMapper.toEntity(personalInfoDto);
        personalInfo.setAddress(address);
        applicant.setPersonalInfo(personalInfo);

        // 3. Extract and map PhD details
        PhdDto phdDto = applicantDto.getPhd();
        Phd phd = applicantMapper.toEntity(phdDto);
        applicant.setPhd(phd);

        // 4. Extract and map Work Experience
        WorkExperienceDto workExperienceDto = applicantDto.getWorkExperience();

        if (!workExperienceDto.isFresher()) {

            List<WorkExperience> workExperiences = workExperienceDto.getList().stream()
                    .map(applicantMapper::toEntity)
                    .toList();
            applicant.setWorkExperience(workExperiences);
        }

        // 5. Extract and map Qualifications
        List<QualificationDto> qualificationDtos = applicantDto.getQualifications();

        List<Qualifications> qualifications = qualificationDtos.stream()
                .map(applicantMapper::toEntity)
                .toList();
        applicant.setQualifications(qualifications);

        // 6. Save the Applicant entity
        return applicantRepository.save(applicant);


    }
    
}
