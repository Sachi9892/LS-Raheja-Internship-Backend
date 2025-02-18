package com.ls_raheja.application_form.service;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.ls_raheja.application_form.dto.degree_dto.AddressDto;
import com.ls_raheja.application_form.dto.degree_dto.ApplicantDto;
import com.ls_raheja.application_form.dto.degree_dto.AwardDto;
import com.ls_raheja.application_form.dto.degree_dto.CompetitiveExamsDto;
import com.ls_raheja.application_form.dto.degree_dto.CourseTaughtDto;
import com.ls_raheja.application_form.dto.degree_dto.PersonalInfoDto;
import com.ls_raheja.application_form.dto.degree_dto.PhdDto;
import com.ls_raheja.application_form.dto.degree_dto.QualificationDto;
import com.ls_raheja.application_form.dto.degree_dto.ResearchPaperDto;
import com.ls_raheja.application_form.dto.degree_dto.WorkExperienceDetailDto;
import com.ls_raheja.application_form.dto.degree_dto.WorkExperienceDto;

public class PDFGenerateService {

    public static void generatePDF(ApplicantDto applicant, String filePath) throws IOException {
  
        PdfWriter writer = new PdfWriter(filePath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Title
        document.add(new Paragraph("L S Raheja Application Form").setFontSize(14));

        // 🔹 Personal Information
        PersonalInfoDto personalInfo = applicant.getPersonalInfo();
        document.add(new Paragraph("Personal Information:"));
        document.add(new Paragraph("Name: " + personalInfo.getFirstName() + " " + personalInfo.getMiddleName() + " " + personalInfo.getLastName()));
        document.add(new Paragraph("Email: " + personalInfo.getEmail()));
        document.add(new Paragraph("Phone: " + personalInfo.getPhone()));
        document.add(new Paragraph("DOB: " + personalInfo.getDob()));
        document.add(new Paragraph("Gender: " + personalInfo.getGender()));
        document.add(new Paragraph("Marital Status: " + personalInfo.getMaritalStatus()));
        document.add(new Paragraph("Number of Children: " + personalInfo.getNoOfChilds()));
        document.add(new Paragraph("Caste: " + personalInfo.getCaste()));
        document.add(new Paragraph("Aadhar: " + personalInfo.getAadhar()));
        document.add(new Paragraph("PAN: " + personalInfo.getPan()));
        document.add(new Paragraph("Passport: " + personalInfo.getPassport()));

        // 🔹 Address
        AddressDto address = applicant.getAddress();
        document.add(new Paragraph("Address:"));
        document.add(new Paragraph("State: " + address.getState()));
        document.add(new Paragraph("City: " + address.getCity()));
        document.add(new Paragraph("Pin Code: " + address.getPinCode()));

        // 🔹 Qualifications
        document.add(new Paragraph("Qualifications:"));
        for (QualificationDto qualification : applicant.getQualifications()) {
            document.add(new Paragraph(
                qualification.getDegree() + " in " + qualification.getDegreeName() +
                " from " + qualification.getUniversityName() +
                " (" + qualification.getYearOfPassing() + "), CGPA: " + qualification.getCgpa()
            ));
        }

        // 🔹 Competitive Exams
        document.add(new Paragraph("Competitive Exams:"));
        for (CompetitiveExamsDto exam : applicant.getCompetitiveExams()) {
            document.add(new Paragraph(
                exam.getExamName() + " - Appeared: " + exam.getIsAppeared() +
                (exam.getYearOfPassing() != null ? ", Year: " + exam.getYearOfPassing() : "")
            ));
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
            document.add(new Paragraph("Conference Presentation: " + phd.getPresentedInConference()));
        }

        // 🔹 Awards
        AwardDto award = applicant.getAwardDto();
        if (award != null) {
            document.add(new Paragraph("Awards:"));
            document.add(new Paragraph("Title: " + award.getTitle()));
            document.add(new Paragraph("Organization: " + award.getOrgName()));
            document.add(new Paragraph("Nature: " + award.getNature()));
            document.add(new Paragraph("Recognized By: " + award.getOrgRecorgnize()));
        }

        // 🔹 Courses Taught
        CourseTaughtDto course = applicant.getCourseDto();
        if (course != null) {
            document.add(new Paragraph("Courses Taught:"));
            document.add(new Paragraph("College Name: " + course.getCollegeName()));
            document.add(new Paragraph("Class Name: " + course.getClassName()));
            document.add(new Paragraph("Subject Name: " + course.getSubjectName()));
            document.add(new Paragraph("Degree Type: " + course.getDegreeType()));
            document.add(new Paragraph("Type of Contract: " + course.getTypeOfContract()));
            document.add(new Paragraph("From Date: " + course.getFromDate()));
            document.add(new Paragraph("To Date: " + course.getToDate()));
            document.add(new Paragraph("Years of Experience: " + course.getYearOfExp()));
            document.add(new Paragraph("Last Salary: " + course.getLastSalary()));
            document.add(new Paragraph("Approved by University: " + course.isApprovedByUniversity()));
            document.add(new Paragraph("Letter Number: " + course.getLetterNO()));
            document.add(new Paragraph("Letter Date: " + course.getLetterDate()));
        }

        // 🔹 Research Papers
        document.add(new Paragraph("Research Papers:"));
        for (ResearchPaperDto researchPaper : applicant.getResearchPaper()) {
            document.add(new Paragraph(
                "Title: " + researchPaper.getTitle() +
                ", Name of the journal: " + researchPaper.getNameOfJournal() +
                ", Year: " + researchPaper.getYearOfPublication() +
                ",Number of approved paper: " + researchPaper.getNumberOfApproved()
            ));

            document.add(new Paragraph(
                "No Of Scoups Indexed Published : " + researchPaper.getScopusIndexedPublications() +
                "Scoups ID : " + researchPaper.getScopusId() + 
                "Paper presented in conference :" + researchPaper.getPresentedInConference()
            ));
        }

        // 🔹 Additional Information
        document.add(new Paragraph("Additional Information:"));
        document.add(new Paragraph("Reference Name: " + applicant.getRefrenceName()));
        document.add(new Paragraph("Expected Salary: " + applicant.getExpectedSalary()));
        document.add(new Paragraph("Applied For Specialization: " + applicant.getAppliedForSpecialization()));
        document.add(new Paragraph("Extra Activity: " + applicant.getExtraActivity()));

        document.close();
    }
    
}

