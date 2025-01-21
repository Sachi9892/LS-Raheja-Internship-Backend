package com.ls_raheja.application_form.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ls_raheja.application_form.exception.FileUploadExcpetion;

@Service
public class FileUploadService {

    private static final String UPLOAD_DIR = "uploads/resumes/";

    @SuppressWarnings("null")
    public String uploadFile(MultipartFile file) throws IOException {

        // Validate file type
        if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new FileUploadExcpetion("Invalid file type. Only PDF files are allowed.");
        }

        // Validate file size (5 MB = 5 * 1024 * 1024 bytes)
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new FileUploadExcpetion("File size exceeds the maximum limit of 5 MB.");
        }

        // Ensure upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save file
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        File dest = new File(filePath);
        file.transferTo(dest);

        return "File uploaded successfully: " + filePath;
    }

}
