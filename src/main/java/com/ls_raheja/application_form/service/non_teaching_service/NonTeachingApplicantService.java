package com.ls_raheja.application_form.service.non_teaching_service;

import org.springframework.stereotype.Service;

import com.ls_raheja.application_form.dto.non_teaching.NTApplicantDTO;
import com.ls_raheja.application_form.entity.non_teaching.NonTeachingApplicant;
import com.ls_raheja.application_form.mapper.NonTeachingMapper;
import com.ls_raheja.application_form.repository.NonTeachingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NonTeachingApplicantService {
    
    private final NonTeachingRepository nRepository;
    private final NonTeachingMapper nonTeachingMapper;

     public NTApplicantDTO saveNonTeachingApplicant(NTApplicantDTO dto) {

        // Convert DTO to Entity
        NonTeachingApplicant applicant = nonTeachingMapper.toEntity(dto);

        // Save the entity
        NonTeachingApplicant savedApplicant = nRepository.save(applicant);

        // Convert back to DTO and return
        return nonTeachingMapper.toDto(savedApplicant);
        
    }
}
