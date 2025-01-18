package com.ls_raheja.application_form.dto;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitiveExamsDto {

    private String examName;

    private boolean isAppeared;

    private LocalDate yearOfPassing;

}
