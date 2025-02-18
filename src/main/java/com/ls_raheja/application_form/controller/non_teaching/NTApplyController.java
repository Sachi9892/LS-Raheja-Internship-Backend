package com.ls_raheja.application_form.controller.non_teaching;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ls_raheja.application_form.dto.non_teaching.NTApplicantDTO;
import com.ls_raheja.application_form.service.non_teaching_service.NonTeachingApplicantService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
public class NTApplyController {
    

    private final NonTeachingApplicantService nTService;

    @PostMapping("/lsraheja/non-teaching")
    public ResponseEntity<NTApplicantDTO> postMethodName(@RequestBody NTApplicantDTO dto) {
        NTApplicantDTO savedDto = nTService.saveNonTeachingApplicant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }
    
}
