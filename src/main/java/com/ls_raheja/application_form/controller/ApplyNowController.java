package com.ls_raheja.application_form.controller;

import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.service.ApplicantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/lsraheja/apply-now")
public class ApplyNowController {

    private final ApplicantService applicantService;

    public ApplyNowController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ResponseEntity<Applicant> applyNow(@RequestBody ApplicantDto applicantDto) {

        Applicant newApplicant = applicantService.saveApplicant(applicantDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newApplicant);


    }
}
