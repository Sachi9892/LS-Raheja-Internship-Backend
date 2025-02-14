package com.ls_raheja.application_form.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ls_raheja.application_form.constants.AppConstants;
import com.ls_raheja.application_form.exception.FileUploadExcpetion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileUploadService {

    @SuppressWarnings("null")
    public String uploadFile(MultipartFile file, String applicantName) throws IllegalStateException, IOException {

        // Validate file type
        if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new FileUploadExcpetion("Invalid file type. Only PDF files are allowed.");
        }

        // Validate file size (5 MB)
        if (file.getSize() > AppConstants.RESUME_MAX_SIZE) {
            throw new FileUploadExcpetion("File size exceeds the maximum limit of 5 MB.");
        }

        // Create a new folder based on submission date
        String submissionFolder = AppConstants.UPLOAD_DIR + File.separator + applicantName + File.separator;
        File uploadDir = new File(submissionFolder);

        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + submissionFolder);
        }

        String fileName = file.getOriginalFilename();
        String filePath = submissionFolder + fileName; // Now includes applicant's directory

        // Save the file
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        
        return filePath;
        
    }

}
