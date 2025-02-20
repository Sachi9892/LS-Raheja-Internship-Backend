package com.ls_raheja.application_form.service.non_teaching_service;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.ls_raheja.application_form.dto.non_teaching.NTAdditionalInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTApplicantDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTPersonalInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTQualificationInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTWorkExpDetailsDTO;

public class NTPDFGenerator {
    
    public static void generatePDF(NTApplicantDTO applicant, String filePath) throws IOException {
        
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Application Form").setFontSize(14));
        NTPersonalInfoDTO personalInfo = applicant.getPersonalInfo();
        document.add(new Paragraph("Personal Information:"));
        document.add(new Paragraph("Name: " + personalInfo.getFirstName() + " " + personalInfo.getMiddleName() + " " + personalInfo.getLastName()));
        document.add(new Paragraph("Email: " + personalInfo.getEmail()));
        document.add(new Paragraph("Mobile: " + personalInfo.getMobileNumber()));
        document.add(new Paragraph("Address: " + personalInfo.getAddress()));
        document.add(new Paragraph("DOB: " + personalInfo.getDob()));

        document.add(new Paragraph("Qualifications:"));
        for (NTQualificationInfoDTO qualification : applicant.getQualificationInfo()) {
            document.add(new Paragraph(qualification.getDegree() + " - " + qualification.getMarks() + " marks, " + qualification.getGrade() + " grade, " + qualification.getYearOfPassing() + " from " + qualification.getUniversityName()));
        }

        document.add(new Paragraph("Work Experience:"));
        if (applicant.getIsFresher()) {
            document.add(new Paragraph("Fresher - No work experience"));
        } else {
            for (NTWorkExpDetailsDTO work : applicant.getWorkExp()) {
                document.add(new Paragraph(work.getPosition() + " at " + work.getOrgName() + " (" + work.getFromDate() + " - " + (work.getToDate().isEmpty() ? "Present" : work.getToDate()) + ") Salary: " + work.getSalary()));
            }
        }

        document.add(new Paragraph("Additional Information:"));
        NTAdditionalInfoDTO addInfo = applicant.getAddInfo();
        document.add(new Paragraph("Expected Salary: " + addInfo.getExpectedSalary()));
        document.add(new Paragraph("Comment: " + addInfo.getComment()));
        
        document.close();
    }
}
