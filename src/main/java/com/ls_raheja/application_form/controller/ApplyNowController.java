package com.ls_raheja.application_form.controller;

import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.repository.ApplicantRepository;
import com.ls_raheja.application_form.service.ApplicantService;
import com.ls_raheja.application_form.service.FileUploadService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
public class ApplyNowController {

    private final FileUploadService fileUploadService;
    private final ApplicantRepository applicantRepository;
    private final ApplicantService applicantService;

    @PostMapping(value = "lsraheja/apply-now", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> applyNow(
            @RequestPart("applicant") ApplicantDto applicantDto,
            @RequestPart("resume") MultipartFile resumeFile) {

        log.info("Received application: {}", applicantDto);

        try {
            // Save the applicant data first
            log.info("Saving applicant data: {}", applicantDto);
            Applicant newApplicant = applicantService.saveApplicant(applicantDto);
            log.info("Applicant saved successfully with ID: {}", newApplicant.getApplicantId());

            // If resume is provided, upload and store file location
            if (resumeFile != null && !resumeFile.isEmpty()) {
                log.info("Uploading resume file: {}", resumeFile.getOriginalFilename());
                String fileName = fileUploadService.uploadFile(resumeFile);
                log.info("Resume uploaded to: {}", fileName);

                newApplicant.setResumeFileLocation(fileName);
            } else {
                log.warn("No resume file provided.");
            }

            // Save the applicant with resume file location
            log.info("Saving applicant with resume file location...");
            applicantRepository.save(newApplicant);
            log.info("Application process completed.");

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(newApplicant);

        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getClass().getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getClass().getName());
        }

    }

}