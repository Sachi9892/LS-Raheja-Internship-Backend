package com.ls_raheja.application_form.service.non_teaching_service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ls_raheja.application_form.dto.non_teaching.NTAdditionalInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTApplicantDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTPersonalInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTQualificationInfoDTO;
import com.ls_raheja.application_form.dto.non_teaching.NTWorkExpDetailsDTO;

public class NTExcelGenerator {

    public static void generateExcel(NTApplicantDTO applicant, String fileName)
            throws FileNotFoundException, IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Applicant Details");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Personal Information");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        NTPersonalInfoDTO personalInfo = applicant.getPersonalInfo();
        createRow(sheet, rowNum++, "Name",
                personalInfo.getFirstName() + " " + personalInfo.getMiddleName() + " " + personalInfo.getLastName());
        createRow(sheet, rowNum++, "Email", personalInfo.getEmail());
        createRow(sheet, rowNum++, "Mobile", personalInfo.getMobileNumber());
        createRow(sheet, rowNum++, "Address", personalInfo.getAddress());
        createRow(sheet, rowNum++, "DOB", personalInfo.getDob());

        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Qualifications");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        for (NTQualificationInfoDTO qualification : applicant.getQualificationInfo()) {
            createRow(sheet, rowNum++, String.valueOf(qualification.getDegree()),
                    qualification.getMarks() + " marks, " + qualification.getGrade() + " grade, "
                            + qualification.getYearOfPassing() + " from " + qualification.getUniversityName());
        }

        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Work Experience");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        if (applicant.getIsFresher()) {
            createRow(sheet, rowNum++, "Fresher", "No work experience");
        } else {
            for (NTWorkExpDetailsDTO work : applicant.getWorkExp()) {
                createRow(sheet, rowNum++, work.getPosition(),
                        work.getOrgName() + " (" + work.getFromDate() + " - "
                                + (work.getToDate().isEmpty() ? "Present" : work.getToDate()) + ") Salary: "
                                + work.getSalary());
            }
        }

        rowNum++;
        headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Additional Information");
        headerRow.getCell(0).setCellStyle(headerCellStyle);

        NTAdditionalInfoDTO addInfo = applicant.getAddInfo();
        createRow(sheet, rowNum++, "Expected Salary", addInfo.getExpectedSalary().toString());
        createRow(sheet, rowNum++, "Comment", addInfo.getComment());

        for (int i = 0; i < 2; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
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
