package com.ls_raheja.application_form.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.ls_raheja.application_form.constants.AppConstants;
import com.ls_raheja.application_form.dto.ApplicantDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FormDataService {
    
    public void generateApplicantFiles(ApplicantDto applicantDto, String applicantName) throws IOException {

        // Define the applicant's directory
        String submissionFolder = AppConstants.UPLOAD_DIR + File.separator + applicantName;
        File folder = new File(submissionFolder);

        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Failed to create directory: " + submissionFolder);
        }

        // Correct paths to include the submissionFolder
        String pdfPath = submissionFolder + File.separator + applicantName + ".pdf";
        String docxPath = submissionFolder + File.separator + applicantName + ".docx";

        PDFGenerateService.generatePDF(applicantDto, pdfPath);
        WordGeneratorService.generateWord(applicantDto, docxPath);

        log.info("Pdf File saved successfully: {}", pdfPath);
        log.info("Docx File saved successfully: {}", docxPath);

        
    }
}
