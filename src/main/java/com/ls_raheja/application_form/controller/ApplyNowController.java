package com.ls_raheja.application_form.controller;

import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.entity.Applicant;
import com.ls_raheja.application_form.repository.ApplicantRepository;
import com.ls_raheja.application_form.service.ApplicantService;
import com.ls_raheja.application_form.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/lsraheja")
@CrossOrigin("*")
@Slf4j
public class ApplyNowController {

    private final ApplicantService applicantService;
    private final FileUploadService fileUploadService;
    private final ApplicantRepository applicantRepository;

    public ApplyNowController(ApplicantService applicantService, FileUploadService fileUploadService,
            ApplicantRepository applicantRepository) {
        this.applicantService = applicantService;
        this.fileUploadService = fileUploadService;
        this.applicantRepository = applicantRepository;
    }

    @PostMapping(value = "/apply-now", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Applicant> applyNow(
            @RequestPart("applicant") ApplicantDto applicantDto,
            @RequestPart("resume") MultipartFile resumeFile)  {

        log.info("From front end : {}", applicantDto);

        try {
            // Save the file and get the file name
            String fileName = fileUploadService.uploadFile(resumeFile);
            log.info("Resume file name {} ", fileName);

            // Save applicant data
            Applicant newApplicant = applicantService.saveApplicant(applicantDto);
            newApplicant.setResumeFileLocation(fileName);
            applicantRepository.save(newApplicant);

            // Return response
            return ResponseEntity.status(HttpStatus.CREATED).body(newApplicant);
        } catch (FileUploadException e) {
            log.error("Error uploading file: {}", e.getMessage());
            
        } catch (IllegalStateException e) {
                    log.info("Illegal wala", e);
                    e.printStackTrace();
                } catch (IOException e) {
                    log.info("I o wala");
                    e.printStackTrace();
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}