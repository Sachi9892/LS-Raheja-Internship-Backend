package com.ls_raheja.application_form.mapper;

import java.util.HashMap;
import java.util.Map;

import com.ls_raheja.application_form.dto.ApplicantDto;

public class FileMapper {

    private FileMapper() {

    }

    public static Map<String, Object> convertApplicantDtoToMap(ApplicantDto dto) {
        Map<String, Object> map = new HashMap<>();
        map.put("personalInfo", dto.getPersonalInfo());
        map.put("address", dto.getAddress());
        map.put("qualifications", dto.getQualifications());
        map.put("competitiveExams", dto.getCompetitiveExams());
        map.put("workExperience", dto.getWorkExperience());
        map.put("phd", dto.getPhd());
        return map;

    }

}
