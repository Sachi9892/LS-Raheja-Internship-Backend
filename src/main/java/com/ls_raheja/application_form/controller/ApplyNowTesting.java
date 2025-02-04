package com.ls_raheja.application_form.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.service.ApplicantService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/lsraheja/test")
@CrossOrigin("*")
@Slf4j
public class ApplyNowTesting {

    private final ApplicantService applicantService;

    public ApplyNowTesting(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Applicant> applyNow(@RequestBody ApplicantDto applicantDto) {
        log.info("From front end : {} ", applicantDto);
        Applicant newApplicant = applicantService.saveApplicant(applicantDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newApplicant);
    }

}
