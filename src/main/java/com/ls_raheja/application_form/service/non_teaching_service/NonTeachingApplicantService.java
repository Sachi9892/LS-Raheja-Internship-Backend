package com.ls_raheja.application_form.service.non_teaching_service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.ls_raheja.application_form.dto.non_teaching.NTApplicantDTO;
import com.ls_raheja.application_form.entity.non_teaching.NTAddtionalInfo;
import com.ls_raheja.application_form.entity.non_teaching.NTPersonalInfo;
import com.ls_raheja.application_form.entity.non_teaching.NTQualificationInfo;
import com.ls_raheja.application_form.entity.non_teaching.NTWorkExpDetails;
import com.ls_raheja.application_form.entity.non_teaching.NonTeachingApplicant;
import com.ls_raheja.application_form.mapper.NonTeachingMapper;
import com.ls_raheja.application_form.repository.NonTeachingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NonTeachingApplicantService {
    
    private final NonTeachingRepository nRepository;
    private final NonTeachingMapper nonTeachingMapper;

     public NonTeachingApplicant saveNonTeachingApplicant(NTApplicantDTO dto) {

         // Create new entity
         NonTeachingApplicant applicant = new NonTeachingApplicant();

         // 1. Map Personal Info
         if (dto.getPersonalInfo() != null) {
             NTPersonalInfo personalInfo = nonTeachingMapper.toEntity(dto.getPersonalInfo());
             applicant.setPersonalInfo(personalInfo);
         }

         // 2. Map Qualification Info
         if (dto.getQualificationInfo() != null) {
             List<NTQualificationInfo> qualifications = dto.getQualificationInfo().stream()
                     .map(nonTeachingMapper::toEntity)
                     .peek(q -> q.setNtApplicant(applicant)) // Set relationship
                     .collect(Collectors.toList());
             applicant.setQualificationInfo(qualifications);
         }

         // 3. Map Additional Info
         if (dto.getAddInfo() != null) {
             NTAddtionalInfo addInfo = nonTeachingMapper.toEntity(dto.getAddInfo());
             applicant.setAddInfo(addInfo);
         }


         // 4. Map Work Experience (only if not a fresher)
         if (dto.getWorkExp() != null && Boolean.FALSE.equals(dto.getIsFresher())) {
             List<NTWorkExpDetails> workExperiences = dto.getWorkExp().stream()
                     .map(nonTeachingMapper::toEntity)
                     .peek(we -> we.setNtApplicant(applicant)) // Set relationship
                     .collect(Collectors.toList());
             applicant.setWorkExp(workExperiences);
         }

         // 5. Set isFresher flag
         applicant.setIsFresher(dto.getIsFresher());

         // Save the entity
         return nRepository.save(applicant);

     }
   
}
