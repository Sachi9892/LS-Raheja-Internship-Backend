package com.ls_raheja.application_form.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

public class ExcelFileService {
    
     public static void generateExcel(ApplicantDto applicant, String filePath) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Applicant Details");

        // Create a bold font for headers
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // 🔹 Personal Information
        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Personal Information");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        PersonalInfoDto personalInfo = applicant.getPersonalInfo();
        createRow(sheet, rowNum++, "Name", personalInfo.getFirstName() + " " + personalInfo.getMiddleName() + " " + personalInfo.getLastName());
        createRow(sheet, rowNum++, "Email", personalInfo.getEmail());
        createRow(sheet, rowNum++, "Phone", personalInfo.getPhone());
        createRow(sheet, rowNum++, "DOB",  personalInfo.getDob().toString());
        createRow(sheet, rowNum++, "Gender", String.valueOf(personalInfo.getGender()));
        createRow(sheet, rowNum++, "Marital Status", String.valueOf(personalInfo.getMaritalStatus()));
        createRow(sheet, rowNum++, "Number of Children", personalInfo.getNoOfChilds().toString());
        createRow(sheet, rowNum++, "Caste", personalInfo.getCaste());
        createRow(sheet, rowNum++, "Aadhar", String.valueOf(personalInfo.getAadhar()));
        createRow(sheet, rowNum++, "PAN", personalInfo.getPan());
        createRow(sheet, rowNum++, "Passport", personalInfo.getPassport());

        // 🔹 Address
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Address");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        AddressDto address = applicant.getAddress();
        createRow(sheet, rowNum++, "State", String.valueOf(address.getState()));
        createRow(sheet, rowNum++, "City", address.getCity());
        createRow(sheet, rowNum++, "Pin Code", address.getPinCode());

        // 🔹 Qualifications
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Qualifications");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        for (QualificationDto qualification : applicant.getQualifications()) {
            createRow(sheet, rowNum++,String.valueOf(qualification.getDegree()), qualification.getDegreeName() + " from " + qualification.getUniversityName() + " (" + qualification.getYearOfPassing() + ")");
        }

        // 🔹 Competitive Exams
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Competitive Exams");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        for (CompetitiveExamsDto exam : applicant.getCompetitiveExams()) {
            createRow(sheet, rowNum++, exam.getExamName(), "Appeared: " + exam.getIsAppeared() + (exam.getYearOfPassing() != null ? ", Year: " + exam.getYearOfPassing() : ""));
        }

        // 🔹 Work Experience
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Work Experience");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        WorkExperienceDto workExperience = applicant.getWorkExperience();
        if (workExperience.getIsFresher()) {
            createRow(sheet, rowNum++, "Fresher", "");
        } else {
            for (WorkExperienceDetailDto work : workExperience.getList()) {
                createRow(sheet, rowNum++, work.getJobTitle(), work.getOrganizationName() + " (" + work.getFromDate() + " - " + (work.getToDate() != null ? work.getToDate() : "Present") + ")");
                createRow(sheet , rowNum++ , "Salary" , work.getCurrentSalary());
                createRow(sheet, rowNum++, "Notice Period",String.valueOf(work.getNoticePeriod()));

            }
        }

        // 🔹 PhD Information
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("PhD Details");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        PhdDto phd = applicant.getPhd();
        if (phd != null) {
            createRow(sheet, rowNum++, "Status", String.valueOf(phd.getStatus()));
            createRow(sheet, rowNum++, "University", phd.getUniversityName());
            createRow(sheet, rowNum++, "Year of Passing", phd.getYearOfPassing().toString());
            createRow(sheet, rowNum++, "Conference Presentation", phd.getPresentedInConference().toString());
        }

        // 🔹 Awards
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Awards");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        AwardDto award = applicant.getAwardDto();
        if (award != null) {
            createRow(sheet, rowNum++, "Title", award.getTitle());
            createRow(sheet, rowNum++, "Organization", award.getOrgName());
            createRow(sheet, rowNum++, "Nature", award.getNature());
            createRow(sheet, rowNum++, "Recognized By", award.getOrgRecorgnize());
        }

        // 🔹 Courses Taught
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Courses Taught");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        CourseTaughtDto course = applicant.getCourseDto();
        if (course != null) {
            createRow(sheet, rowNum++, "College Name", course.getCollegeName());
            createRow(sheet, rowNum++, "Class Name", course.getClassName());
            createRow(sheet, rowNum++, "Subject Name", course.getSubjectName());
            createRow(sheet, rowNum++, "Degree Type", course.getDegreeType());
            createRow(sheet, rowNum++, "Type of Contract", course.getTypeOfContract());
            createRow(sheet, rowNum++, "From Date", course.getFromDate().toString());
            createRow(sheet, rowNum++, "To Date", course.getToDate().toString());
            createRow(sheet, rowNum++, "Years of Experience", course.getYearOfExp());
            createRow(sheet, rowNum++, "Last Salary", course.getLastSalary().toString());
            createRow(sheet, rowNum++, "Approved by University", course.isApprovedByUniversity() ? "Yes" : "No");
            createRow(sheet, rowNum++, "Letter Number", course.getLetterNO());
            createRow(sheet, rowNum++, "Letter Date", course.getLetterDate().toString());
        }

        // 🔹 Research Papers
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Research Papers");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        for (ResearchPaperDto researchPaper : applicant.getResearchPaper()) {
            createRow(sheet, rowNum++, researchPaper.getTitle(), researchPaper.getNameOfJournal() + " (" + researchPaper.getYearOfPublication() + ")");
        }

        // 🔹 Additional Information
        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Additional Information");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        createRow(sheet, rowNum++, "Reference Name", applicant.getRefrenceName());
        createRow(sheet, rowNum++, "Expected Salary", applicant.getExpectedSalary().toString());
        createRow(sheet, rowNum++, "Applied For Specialization", applicant.getAppliedForSpecialization());
        createRow(sheet, rowNum++, "Extra Activity", applicant.getExtraActivity());

        // Auto-size columns
        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    private static void createRow(Sheet sheet, int rowNum, String label, String value) {
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(label);
        row.createCell(1).setCellValue(value);
    }

}
