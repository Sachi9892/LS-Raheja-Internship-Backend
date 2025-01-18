package com.ls_raheja.application_form.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class PhdDto {

    private String status;
    private String universityName;
    private LocalDate yearOfPassing;
    private int scopusIndexedPublications;
    private String scopusId;
    private boolean presentedInConference;
    private int wosIndexedPublications;
    private String wosId;
}
