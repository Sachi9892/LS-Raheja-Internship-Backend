package com.ls_raheja.application_form.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PDFGenerateService {

    
    @SuppressWarnings("unchecked")
    public static void generatePDF(Map<String, Object> formData, String filePath) throws IOException {
        
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Document title = document.add(new Paragraph("L S Raheja Application Form"));
        title.setFontSize(14);

        // Personal Information
        Map<String, String> personalInfo = (Map<String, String>) formData.get("personalInfo");
        if (personalInfo != null) {
            document.add(new Paragraph("Personal Information").setFontSize(14));
            document.add(new Paragraph("Name: " + personalInfo.get("firstName") + " " + personalInfo.get("middleName")
                    + " " + personalInfo.get("lastName")));
            document.add(new Paragraph("Email: " + personalInfo.get("email")));
            document.add(new Paragraph("Phone: " + personalInfo.get("phone")));
            document.add(new Paragraph("DOB: " + personalInfo.get("dob")));
            document.add(new Paragraph("Gender: " + personalInfo.get("gender")));
        }

        // Address
        Map<String, String> address = (Map<String, String>) formData.get("address");
        if (address != null) {
            document.add(new Paragraph("\nAddress").setFontSize(14));
            document.add(new Paragraph("State: " + address.get("state") + ", City: " + address.get("city")
                    + ", Pin Code: " + address.get("pinCode")));
        }

        // Qualifications
        List<Map<String, String>> qualifications = (List<Map<String, String>>) formData.get("qualifications");
        if (qualifications != null && !qualifications.isEmpty()) {
            document.add(new Paragraph("\nQualifications").setFontSize(14));
            for (Map<String, String> qualification : qualifications) {
                document.add(new Paragraph("Degree: " + qualification.get("degree") + ", University: "
                        + qualification.get("universityName")));
                document.add(new Paragraph("Specialization: " + qualification.get("specialization") + ", Year: "
                        + qualification.get("yearOfPassing")));
            }
        }

        // Work Experience
        Map<String, Object> workExperience = (Map<String, Object>) formData.get("workExperience");
        if (workExperience != null) {
            List<Map<String, String>> workList = (List<Map<String, String>>) workExperience.get("list");
            if (workList != null && !workList.isEmpty()) {
                document.add(new Paragraph("\nWork Experience").setFontSize(14));
                for (Map<String, String> work : workList) {
                    document.add(new Paragraph(
                            "Company: " + work.get("organizationName") + ", Job Title: " + work.get("jobTitle")));
                    document.add(new Paragraph("From: " + work.get("fromDate") + " To: " + work.get("toDate")));
                }
            }
        }

        document.close();
        System.out.println("PDF created successfully: " + filePath);
    }
}


