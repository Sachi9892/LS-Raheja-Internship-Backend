package com.ls_raheja.application_form.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.ls_raheja.application_form.constants.AppConstants;
import com.ls_raheja.application_form.dto.degree_dto.ApplicantDto;

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
        String excelPath = submissionFolder + File.separator + applicantName + ".xlsx";

        PDFGenerateService.generatePDF(applicantDto, pdfPath);
        ExcelFileService.generateExcel(applicantDto, excelPath);

        log.info("Pdf File saved successfully: {}", pdfPath);
        log.info("Excel File saved successfully: {}", excelPath);

        
    }
}
