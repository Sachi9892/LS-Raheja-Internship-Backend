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

        // Define the folder where files will be stored
        String submissionFolder = AppConstants.UPLOAD_DIR + File.separator + applicantName;

        // Ensure directory exists
        File folder = new File(submissionFolder);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Failed to create directory: " + submissionFolder);
        }

        String pdfPath = AppConstants.UPLOAD_DIR + applicantName + ".pdf";
        String docxPath = AppConstants.UPLOAD_DIR + applicantName + ".docx";

        PDFGenerateService.generatePDF(applicantDto, pdfPath);
        WordGeneratorService.generateWord(applicantDto, docxPath);

        log.info("Pdf File saved successfully: {} ", pdfPath);
        log.info("Docx File saved successfully: {} ", docxPath);
        
    }
}
