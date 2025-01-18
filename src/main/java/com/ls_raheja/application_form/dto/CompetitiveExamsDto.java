package com.ls_raheja.application_form.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class CompetitiveExamsDto {

    private String examName;

    private boolean isAppeared;

    private LocalDate yearOfPassing;

}
