package com.ls_raheja.application_form.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ls_raheja.application_form.constants.AppConstants;
import com.ls_raheja.application_form.exception.FileUploadExcpetion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileUploadService {


    @SuppressWarnings("null")
    public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {

        // Validate file type
        if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new FileUploadExcpetion("Invalid file type. Only PDF files are allowed.");
        }

        // Validate file size (5 MB = 5 * 1024 * 1024 bytes)
        if (file.getSize() > AppConstants.RESUME_MAX_SIZE) {
            throw new FileUploadExcpetion("File size exceeds the maximum limit of 5 MB.");
        }

        // Ensure the parent directory (Resumes) exists
        File uploadDir = new File(AppConstants.UPLOAD_DIR);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + AppConstants.UPLOAD_DIR);
        }

        // Generate a unique file name to prevent overwriting
        String fileName = file.getOriginalFilename();
        String filePath = AppConstants.UPLOAD_DIR + File.separator + fileName;

        log.info("Saving file to: {}", filePath);

        // Save the file
        File dest = new File(filePath);
        file.transferTo(dest);

        return filePath; // Returning the stored file path

    }

}
