package com.ls_raheja.application_form.service;

import com.ls_raheja.application_form.dto.*;
import com.ls_raheja.application_form.entity.*;
import com.ls_raheja.application_form.mapper.ApplicantMapper;
import com.ls_raheja.application_form.repository.ApplicantRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        // 1. Extract and map Personal Info , Address and role
        PersonalInfoDto personalInfoDto = applicantDto.getPersonalInfo();
        AddressDto addressDto = applicantDto.getAddress();

        // map dto's tp entity
        Address address = applicantMapper.toEntity(addressDto);
        PersonalInfo personalInfo = applicantMapper.toEntity(personalInfoDto);

        personalInfo.setRole(personalInfoDto.getRole());
        personalInfo.setAddress(address);

        personalInfo.setApplicant(applicant);

        applicant.setPersonalInfo(personalInfo);

        // 3. Extract and map PhD details
        PhdDto phdDto = applicantDto.getPhd();
        Phd phd = applicantMapper.toEntity(phdDto);

        phd.setPresentedInConference(phdDto.getPresentedInConference());
        phd.setApplicant(applicant);
        applicant.setPhd(phd);

        // 4. Extract and map Work Experience
        WorkExperienceDto workExperienceDto = applicantDto.getWorkExperience();
        if (workExperienceDto != null) {
            boolean isFresher = workExperienceDto.getIsFresher();
            List<WorkExperienceDetailDto> experienceList = workExperienceDto.getList();

            List<WorkExperience> workExperiences;

            if (isFresher) {
                WorkExperience fresherExperience = new WorkExperience();
                fresherExperience.setIsFresher(true);
                fresherExperience.setApplicant(applicant);
                workExperiences = new ArrayList<>(); 
                workExperiences.add(fresherExperience);
            } else {
                workExperiences = experienceList.stream()
                        .map(workExperience -> {
                            WorkExperience entity = applicantMapper.toEntity(workExperience);
                            entity.setIsFresher(false); // Not a fresher
                            entity.setIsCurrentlyWorking(Boolean.TRUE.equals(workExperience.getIsCurrentlyWorking()));
                            entity.setApplicant(applicant);
                            return entity;
                        })
                        .collect(Collectors.toList());
            }
            applicant.setWorkExperience(workExperiences);
        }

        // 5. Extract Competitive Exams details
        List<CompetitiveExamsDto> competitiveExamsDtos = applicantDto.getCompetitiveExams();

        competitiveExamsDtos.forEach(dto -> {
            System.out.println("Exam: " + dto.getExamName() + ", Appeared: " + dto.getIsAppeared() + ", Year: "
                    + dto.getYearOfPassing());
        });

        if (competitiveExamsDtos != null) {
            List<CompetitiveExams> competitiveExamsEntities = competitiveExamsDtos.stream()
                    .map(examDto -> {
                        CompetitiveExams exam = new CompetitiveExams();
                        exam.setExamName(examDto.getExamName());
                        exam.setIsAppeared(examDto.getIsAppeared());
                        exam.setYearOfPassing(examDto.getYearOfPassing());
                        ;
                        exam.setApplicant(applicant);
                        return exam;
                    })
                    .collect(Collectors.toList());
            ;

            applicant.setCompetitiveExams(competitiveExamsEntities);
        }

        // 6. Extract and map Qualifications
        List<QualificationDto> qualificationDtos = applicantDto.getQualifications();
        List<Qualifications> qualifications = qualificationDtos.stream()
                .map(dto -> {
                    Qualifications qualification = applicantMapper.toEntity(dto);
                    qualification.setApplicant(applicant);
                    return qualification;
                })
                .collect(Collectors.toList());
        ;
        applicant.setQualifications(qualifications);

        // 7. Save the Applicant entity
        return applicantRepository.save(applicant);

    }

}
