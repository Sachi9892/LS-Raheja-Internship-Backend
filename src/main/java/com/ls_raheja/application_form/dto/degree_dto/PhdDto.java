package com.ls_raheja.application_form.dto.degree_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls_raheja.application_form.entity.degree.PhdStatus;

import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhdDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private PhdStatus status;

    private String universityName;
    private Integer yearOfPassing;
    private Boolean presentedInConference;
}
