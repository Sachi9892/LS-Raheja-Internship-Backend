package com.ls_raheja.application_form.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ls_raheja.application_form.constants.AppConstants;
import com.ls_raheja.application_form.exception.FileUploadExcpetion;

@Service
public class FileUploadService {


    public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {

        // Validate file type
        if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new FileUploadExcpetion("Invalid file type. Only PDF files are allowed.");
        }

        // Validate file size (5 MB = 5 * 1024 * 1024 bytes)
        if (file.getSize() > AppConstants.RESUME_MAX_SIZE) {
            throw new FileUploadExcpetion("File size exceeds the maximum limit of 5 MB.");
        }

        // Ensure upload directory exists
        File uploadDir = new File(AppConstants.UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save file
        String filePath = AppConstants.UPLOAD_DIR + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);

        return filePath;

    }

}
