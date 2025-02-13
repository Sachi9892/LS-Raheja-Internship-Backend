package com.ls_raheja.application_form.controller;

import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.service.ApplicantService;
import com.ls_raheja.application_form.service.FileUploadService;
import com.ls_raheja.application_form.service.FormDataService;

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
    private final ApplicantService applicantService;
    private final FormDataService formDataService;

    @PostMapping(value = "lsraheja/apply-now", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> applyNow(
            @RequestPart("applicant") ApplicantDto applicantDto,
            @RequestPart("resume") MultipartFile resumeFile) {

        log.info("Received application: {}", applicantDto);

        try {

            // 1. Save Applicant
            Applicant newApplicant = applicantService.saveApplicant(applicantDto);
            log.info("Applicant saved successfully with ID: {}", newApplicant.getApplicantId());

            // 2.Save resume
            String phone = newApplicant.getPersonalInfo().getFirstName() + " " + newApplicant.getPersonalInfo().getPhone();

            if (resumeFile != null && !resumeFile.isEmpty()) {
                String fileName = fileUploadService.uploadFile(resumeFile, phone);
                log.info("Resume uploaded to: {}", fileName);
            } else {
                log.warn("No resume file provided.");
            }

            // 3. Generate PDF and DOCX files
            formDataService.generateApplicantFiles(applicantDto , phone);

            log.info("Application process completed.");
            return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");

        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getClass().getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getClass().getName());
        }

    }

}