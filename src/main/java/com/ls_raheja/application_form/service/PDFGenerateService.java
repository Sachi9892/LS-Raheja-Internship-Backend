package com.ls_raheja.application_form.service;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.ls_raheja.application_form.dto.AddressDto;
import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.dto.CompetitiveExamsDto;
import com.ls_raheja.application_form.dto.PersonalInfoDto;
import com.ls_raheja.application_form.dto.PhdDto;
import com.ls_raheja.application_form.dto.QualificationDto;
import com.ls_raheja.application_form.dto.WorkExperienceDetailDto;
import com.ls_raheja.application_form.dto.WorkExperienceDto;

public class PDFGenerateService {

    public static void generatePDF(ApplicantDto applicant, String filePath) throws IOException {

    PdfWriter writer = new PdfWriter(filePath);
    PdfDocument pdf = new PdfDocument(writer);
    Document document = new Document(pdf);

    document.add(new Paragraph("L S Raheja Application Form").setFontSize(14));

    // 🔹 Personal Information
    PersonalInfoDto personalInfo = applicant.getPersonalInfo();
    document.add(new Paragraph("Personal Information:"));
    document.add(new Paragraph("Name: " + personalInfo.getFirstName() + " " + personalInfo.getMiddleName() + " " + personalInfo.getLastName()));
    document.add(new Paragraph("Email: " + personalInfo.getEmail()));
    document.add(new Paragraph("Phone: " + personalInfo.getPhone()));
    document.add(new Paragraph("DOB: " + personalInfo.getDob()));
    document.add(new Paragraph("Gender: " + personalInfo.getGender()));

    // 🔹 Address
    AddressDto address = applicant.getAddress();
    document.add(new Paragraph("Address:"));
    document.add(new Paragraph("State: " + address.getState()));
    document.add(new Paragraph("City: " + address.getCity()));
    document.add(new Paragraph("Pin Code: " + address.getPinCode()));

    // 🔹 Qualifications
    document.add(new Paragraph("Qualifications:"));
    for (QualificationDto qualification : applicant.getQualifications()) {
        document.add(new Paragraph(qualification.getDegree() + " in " + qualification.getDegreeName() + 
            " from " + qualification.getUniversityName() + " (" + qualification.getYearOfPassing() + ")"
            ));
    }

    // 🔹 Competitive Exams
    document.add(new Paragraph("Competitive Exams:"));
    for (CompetitiveExamsDto exam : applicant.getCompetitiveExams()) {
        document.add(new Paragraph(exam.getExamName() + " - Appeared: " + exam.getIsAppeared() + 
            (exam.getYearOfPassing() != null ? ", Year: " + exam.getYearOfPassing() : "")));
    }

    // 🔹 Work Experience
    WorkExperienceDto workExperience = applicant.getWorkExperience();
    document.add(new Paragraph("Work Experience:"));
    if (workExperience.getIsFresher()) {
        document.add(new Paragraph("Fresher"));
    } else {
       for (WorkExperienceDetailDto work : workExperience.getList()) {
            document.add(new Paragraph(
                work.getJobTitle() + " at " + work.getOrganizationName() + 
                " (" + work.getFromDate() + " - " + (work.getToDate() != null ? work.getToDate() : "Present") + ")" +
                " Salary: " + work.getCurrentSalary() + 
                " Notice Period: " + work.getNoticePeriod()
            ));
        }
    }

    // 🔹 PhD Information
    PhdDto phd = applicant.getPhd();
    if (phd != null) {
        document.add(new Paragraph("PhD Details:"));
        document.add(new Paragraph("Status: " + phd.getStatus()));
        document.add(new Paragraph("University: " + phd.getUniversityName()));
        document.add(new Paragraph("Year of Passing: " + phd.getYearOfPassing()));
        document.add(new Paragraph("Scopus Publications: " + phd.getScopusIndexedPublications()));
        document.add(new Paragraph("Scopus ID: " + phd.getScopusId()));
        document.add(new Paragraph("WOS Publications: " + phd.getWosIndexedPublications()));
        document.add(new Paragraph("WOS ID: " + phd.getWosId()));
        document.add(new Paragraph("Conference Presentation ? " + String.valueOf(phd.getPresentedInConference())));
    }

        document.close();

    }

 
    
}

