package com.ls_raheja.application_form.service;

import java.io.FileOutputStream;


import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.ls_raheja.application_form.dto.AddressDto;
import com.ls_raheja.application_form.dto.ApplicantDto;
import com.ls_raheja.application_form.dto.CompetitiveExamsDto;
import com.ls_raheja.application_form.dto.PersonalInfoDto;
import com.ls_raheja.application_form.dto.PhdDto;
import com.ls_raheja.application_form.dto.QualificationDto;
import com.ls_raheja.application_form.dto.WorkExperienceDetailDto;
import com.ls_raheja.application_form.dto.WorkExperienceDto;

import java.io.IOException;

public class WordGeneratorService {

    public static void generateWord(ApplicantDto applicantDto, String filePath) throws IOException {

        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(filePath);

        // Title
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("L S Raheja Application Form");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // Personal Information
        addSectionTitle(document, "Personal Information");
        
        PersonalInfoDto personalInfo = applicantDto.getPersonalInfo();
        addKeyValue(document, "Name", personalInfo.getFirstName() + " " +
                personalInfo.getMiddleName() + " " + personalInfo.getLastName());
        addKeyValue(document, "Email", personalInfo.getEmail());
        addKeyValue(document, "Phone", personalInfo.getPhone());
        addKeyValue(document, "DOB", personalInfo.getDob().toString());
        addKeyValue(document, "Gender", personalInfo.getGender().toString());
        addKeyValue(document, "Role", personalInfo.getRole().toString());

        // Address
        addSectionTitle(document, "Address");
        AddressDto address = applicantDto.getAddress();
        addKeyValue(document, "Address", address.getState() + ", " +
                address.getCity() + " - " + address.getPinCode());

        // Qualifications
        addSectionTitle(document, "Qualifications");
        
        for (QualificationDto q : applicantDto.getQualifications()) {
            addKeyValue(document, "Degree", q.getDegree() + " (" + q.getEducationMode() + ")");
            addKeyValue(document, "Degree Name", q.getDegreeName());
            addKeyValue(document, "University", q.getUniversityName());
            addKeyValue(document, "Specialization", q.getSpecialization());
            addKeyValue(document, "Year & CGPA", q.getYearOfPassing() + " | " + q.getCgpa());
            document.createParagraph(); // Spacer
        }

        // Competitive Exams
        addSectionTitle(document, "Competitive Exams");
        for (CompetitiveExamsDto exam : applicantDto.getCompetitiveExams()) {
            if (exam.getIsAppeared()) {
                addKeyValue(document, "Exam", exam.getExamName() + " (" + exam.getYearOfPassing() + ")");
            }
        }

        // Work Experience
        addSectionTitle(document, "Work Experience");
        WorkExperienceDto workExp = applicantDto.getWorkExperience();
        if (!workExp.getIsFresher()) {
            for (WorkExperienceDetailDto work : workExp.getList()) {
                String toDate = (work.getToDate() == null) ? "Present" : work.getToDate().toString();
                addKeyValue(document, "Company", work.getOrganizationName());
                addKeyValue(document, "Job Title", work.getJobTitle());
                addKeyValue(document, "Period", work.getFromDate().toString() + " - " + toDate);
                addKeyValue(document, "Salary", String.valueOf(work.getCurrentSalary()));
                addKeyValue(document, "Notice Period", work.getNoticePeriod().toString());
                document.createParagraph(); // Spacer
            }
        } else {
            addKeyValue(document, "Work Experience", "Fresher");
        }

        // PhD Details
        addSectionTitle(document, "PhD Details");
        PhdDto phd = applicantDto.getPhd();
        addKeyValue(document, "Status", phd.getStatus().toString());
        addKeyValue(document, "University", phd.getUniversityName());
        addKeyValue(document, "Year", String.valueOf(phd.getYearOfPassing()));
        addKeyValue(document, "Scopus Publications", String.valueOf(phd.getScopusIndexedPublications()));
        addKeyValue(document, "Scopus ID", String.valueOf(phd.getScopusId()));
        addKeyValue(document, "WOS Publications", String.valueOf(phd.getWosIndexedPublications()));
        addKeyValue(document, "WOS ID", String.valueOf(phd.getWosId()));
        addKeyValue(document, "Conference Presentation ? ", String.valueOf(phd.getPresentedInConference()));

        // Write and close document
        document.write(out);
        out.close();
        document.close();
        System.out.println("Word file created: " + filePath);
    }

    private static void addSectionTitle(XWPFDocument doc, String title) {
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();
        run.setText(title);
        run.setBold(true);
        run.setFontSize(14);
        run.addBreak();
    }

    private static void addKeyValue(XWPFDocument doc, String key, String value) {
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();
        run.setText(key + ": " + (value != null ? value : ""));
    }

}