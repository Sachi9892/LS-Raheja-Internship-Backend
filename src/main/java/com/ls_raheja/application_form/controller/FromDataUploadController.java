package com.ls_raheja.application_form.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.service.ApplicantService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class FromDataUploadController {

    private final ApplicantService applicantService;

    @PostMapping(value = "/test")
    public ResponseEntity<Applicant> applyNow(@RequestBody ApplicantDto applicantDto) {
        log.info("Received Applicant Data: {}", applicantDto);
        try {
            Applicant newApplicant = applicantService.saveApplicant(applicantDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newApplicant);
        } catch (Exception e) {
            log.error("Error saving applicant data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
