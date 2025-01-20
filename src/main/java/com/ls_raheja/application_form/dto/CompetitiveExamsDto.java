package com.ls_raheja.application_form.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitiveExamsDto {

    private String examName;

    private Boolean isAppeared;

    private String yearOfPassing;

}
