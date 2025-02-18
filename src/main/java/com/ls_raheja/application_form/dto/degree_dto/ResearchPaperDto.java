package com.ls_raheja.application_form.dto.degree_dto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class ResearchPaperDto {

    private String title;
    private Integer scopusIndexedPublications;

    private String scopusId;

    private Boolean presentedInConference;

    private String nameOfJournal;

    private LocalDate yearOfPublication;

    private Integer numberOfApproved;

}
