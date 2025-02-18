package com.ls_raheja.application_form.dto.degree_dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompetitiveExamsDto {

    private String examName;

    private Boolean isAppeared;
    
    private Integer yearOfPassing;

}
