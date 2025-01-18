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

        //Create a new applicant, extract info from DTO and map it to entity
        Applicant applicant = new Applicant();

        //1.Extract the Role from ApplicantDTO
        applicant.setRole(applicantDto.getRole());

        //2.Extract PersonalInfoDTO from ApplicantDTO
        PersonalInfoDto personalInfoDto = applicantDto.getPersonalInfo();

        //Extract AddressDto from ApplicantDto
        AddressDto addressDto = applicantDto.getAddress();

        //Convert AddressDTO to Address entity
        Address address = applicantMapper.toEntity(addressDto);

        //Convert PersonalInfoDTO to PersonalInfo entity
        PersonalInfo info = applicantMapper.toEntity(personalInfoDto);

        //Set Address to PersonalInfo
        info.setAddress(address);

        //Now set PersonalInfo(with Address) to Applicant
        applicant.setPersonalInfo(info);

        //3.Extract PhdDTO from ApplicantDTO
        PhdDto phdDto = applicantDto.getPhd();

        //Convert PhdDTO to Phd entity
        Phd phd = applicantMapper.toEntity(phdDto);

        //Set Phd to Applicant
        applicant.setPhd(phd);

        //4.Extract WorkExperienceDTO from ApplicantDTO
        List<WorkExperienceDto> workExperienceDtos = applicantDto.getWorkExperience();

        //Convert WorkExperienceDTO to WorkExperience entity
        List<WorkExperience> workExperiences = workExperienceDtos.stream()
                .map(applicantMapper::toEntity)
                .toList();

        //Set WorkExperience to Applicant
        applicant.setWorkExperience(workExperiences);

        //5.Extract QualificationDTO from ApplicantDTO
        List<QualificationDto> qualificationDtos = applicantDto.getQualifications();

        //Convert QualificationDTO to Qualifications entity
        List<Qualifications> qualifications = qualificationDtos.stream()
                .map(applicantMapper::toEntity)
                .toList();

        //Set Qualifications to Applicant
        applicant.setQualifications(qualifications);

        //6.Finally save the Applicant
        return applicantRepository.save(applicant);


    }
}
