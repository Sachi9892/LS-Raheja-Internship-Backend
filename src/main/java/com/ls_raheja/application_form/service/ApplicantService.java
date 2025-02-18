package com.ls_raheja.application_form.service;

import com.ls_raheja.application_form.dto.degree_dto.AddressDto;
import com.ls_raheja.application_form.dto.degree_dto.ApplicantDto;
import com.ls_raheja.application_form.dto.degree_dto.AwardDto;
import com.ls_raheja.application_form.dto.degree_dto.CompetitiveExamsDto;
import com.ls_raheja.application_form.dto.degree_dto.CourseTaughtDto;
import com.ls_raheja.application_form.dto.degree_dto.PersonalInfoDto;
import com.ls_raheja.application_form.dto.degree_dto.PhdDto;
import com.ls_raheja.application_form.dto.degree_dto.QualificationDto;
import com.ls_raheja.application_form.dto.degree_dto.ResearchPaperDto;
import com.ls_raheja.application_form.dto.degree_dto.WorkExperienceDetailDto;
import com.ls_raheja.application_form.dto.degree_dto.WorkExperienceDto;
import com.ls_raheja.application_form.entity.degree.Address;
import com.ls_raheja.application_form.entity.degree.Applicant;
import com.ls_raheja.application_form.entity.degree.Award;
import com.ls_raheja.application_form.entity.degree.CompetitiveExams;
import com.ls_raheja.application_form.entity.degree.CourseTaught;
import com.ls_raheja.application_form.entity.degree.PersonalInfo;
import com.ls_raheja.application_form.entity.degree.Phd;
import com.ls_raheja.application_form.entity.degree.Qualifications;
import com.ls_raheja.application_form.entity.degree.ResearchPublication;
import com.ls_raheja.application_form.entity.degree.WorkExperience;
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

        // 1. Extract and map Personal Info, Address, and Role
        PersonalInfoDto personalInfoDto = applicantDto.getPersonalInfo();
        AddressDto addressDto = applicantDto.getAddress();

        // Map DTOs to entities
        Address address = applicantMapper.toEntity(addressDto);
        PersonalInfo personalInfo = applicantMapper.toEntity(personalInfoDto);

        personalInfo.setRole(personalInfoDto.getRole());
        personalInfo.setAddress(address);
        personalInfo.setApplicant(applicant);

        applicant.setPersonalInfo(personalInfo);

        // 2. Extract and map PhD details
        PhdDto phdDto = applicantDto.getPhd();
        if (phdDto != null) {
            Phd phd = applicantMapper.toEntity(phdDto);
            phd.setApplicant(applicant);
            applicant.setPhd(phd);
        }

        // 3. Extract and map Work Experience
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

        // 4. Extract and map Competitive Exams details
        List<CompetitiveExamsDto> competitiveExamsDtos = applicantDto.getCompetitiveExams();
        if (competitiveExamsDtos != null) {
            List<CompetitiveExams> competitiveExamsEntities = competitiveExamsDtos.stream()
                    .map(examDto -> {
                        CompetitiveExams exam = new CompetitiveExams();
                        exam.setExamName(examDto.getExamName());
                        exam.setIsAppeared(examDto.getIsAppeared());
                        exam.setYearOfPassing(examDto.getYearOfPassing());
                        exam.setApplicant(applicant);
                        return exam;
                    })
                    .collect(Collectors.toList());
            applicant.setCompetitiveExams(competitiveExamsEntities);
        }

        // 5. Extract and map Qualifications
        List<QualificationDto> qualificationDtos = applicantDto.getQualifications();
        if (qualificationDtos != null) {
            List<Qualifications> qualifications = qualificationDtos.stream()
                    .map(dto -> {
                        Qualifications qualification = applicantMapper.toEntity(dto);
                        qualification.setApplicant(applicant);
                        return qualification;
                    })
                    .collect(Collectors.toList());
            applicant.setQualifications(qualifications);
        }

        // 6. Extract and map Awards
        AwardDto awardDto = applicantDto.getAwardDto();
        if (awardDto != null) {
            Award award = applicantMapper.toEntity(awardDto);
            award.setApplicant(applicant);
            applicant.setAward(award);
        }

        // 7. Extract and map Courses Taught
        CourseTaughtDto courseDto = applicantDto.getCourseDto();
        if (courseDto != null) {
            CourseTaught courseTaught = applicantMapper.toEntity(courseDto);
            courseTaught.setApplicant(applicant);
            applicant.setCourseTaught(courseTaught);
        }

        // 8. Extract and map Research Papers
        List<ResearchPaperDto> researchPaperDtos = applicantDto.getResearchPaper();
        if (researchPaperDtos != null) {
            List<ResearchPublication> researchPublications = researchPaperDtos.stream()
                    .map(dto -> {
                        ResearchPublication researchPublication = applicantMapper.toEntity(dto);
                        researchPublication.setApplicant(applicant);
                        return researchPublication;
                    })
                    .collect(Collectors.toList());
            applicant.setResearchPapers(researchPublications);
        }

        // 9. Map additional fields
        applicant.setRefrenceName(applicantDto.getRefrenceName());
        applicant.setExpectedSalary(applicantDto.getExpectedSalary());
        applicant.setAppliedFor(applicantDto.getAppliedForSpecialization());
        applicant.setExtraActivity(applicantDto.getExtraActivity());

        // 10. Save the Applicant entity
        return applicantRepository.save(applicant);

    }

}
