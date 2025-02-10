package com.ls_raheja.application_form.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.repository.ApplicantRepository;
import com.ls_raheja.application_form.service.FileUploadService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@Slf4j
public class UploadFileTesting {

    private final FileUploadService fileUploadService;
    private final ApplicantRepository applicantRepository;

    @PostMapping("/apply-now/upload-resume")
    public ResponseEntity<String> uploadResume(@RequestPart("resume") MultipartFile resumeFile,
            @RequestPart("applicantId") Long applicantId) {
        try {
            String fileName = fileUploadService.uploadFile(resumeFile);
            Applicant applicant = applicantRepository.findById(applicantId).orElseThrow();
            applicant.setResumeFileLocation(fileName);
            applicantRepository.save(applicant);
            return ResponseEntity.ok("Resume uploaded successfully.");
        } catch (Exception e) {
            log.error("Error uploading resume: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Resume upload failed.");
        }
    }

}
