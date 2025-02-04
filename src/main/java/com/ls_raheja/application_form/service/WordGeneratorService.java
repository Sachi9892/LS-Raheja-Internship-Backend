package com.ls_raheja.application_form.service;

import java.io.FileOutputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;

public class WordGeneratorService {

    @SuppressWarnings("unchecked")
    public static void generateWord(Map<String, Object> formData, String filePath) throws IOException {

        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File(filePath));

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Application Form");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // Personal Information
        XWPFParagraph sectionTitle = document.createParagraph();
        sectionTitle.createRun().setText("\nPersonal Information");
        sectionTitle.createRun().setBold(true);

        Map<String, String> personalInfo = (Map<String, String>) formData.get("personalInfo");
        document.createParagraph().createRun().setText("Name: " + personalInfo.get("firstName") + " "
                + personalInfo.get("middleName") + " " + personalInfo.get("lastName"));
        document.createParagraph().createRun().setText("Email: " + personalInfo.get("email"));
        document.createParagraph().createRun().setText("Phone: " + personalInfo.get("phone"));

        // Address
        sectionTitle = document.createParagraph();
        sectionTitle.createRun().setText("\nAddress");
        sectionTitle.createRun().setBold(true);

        Map<String, String> address = (Map<String, String>) formData.get("address");
        document.createParagraph().createRun().setText("State: " + address.get("state") + ", City: "
                + address.get("city") + ", Pin Code: " + address.get("pinCode"));

        // Qualifications
        sectionTitle = document.createParagraph();
        sectionTitle.createRun().setText("\nQualifications");
        sectionTitle.createRun().setBold(true);

        List<Map<String, String>> qualifications = (List<Map<String, String>>) formData.get("qualifications");
        for (Map<String, String> qualification : qualifications) {
            document.createParagraph().createRun().setText(
                    "Degree: " + qualification.get("degree") + ", University: " + qualification.get("universityName"));
        }

        // Work Experience
        sectionTitle = document.createParagraph();
        sectionTitle.createRun().setText("\nWork Experience");
        sectionTitle.createRun().setBold(true);

        Map<String, Object> workExperience = (Map<String, Object>) formData.get("workExperience");
        List<Map<String, String>> workList = (List<Map<String, String>>) workExperience.get("list");
        for (Map<String, String> work : workList) {
            document.createParagraph().createRun()
                    .setText("Company: " + work.get("organizationName") + ", Job Title: " + work.get("jobTitle"));
        }

        document.write(out);
        out.close();
        document.close();
        System.out.println("Word file created successfully: " + filePath);

    }
}
