package com.ls_raheja.application_form.dto;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitiveExamsDto {

    private String examName;

    private boolean isAppeared;

    private String yearOfPassing;

}
