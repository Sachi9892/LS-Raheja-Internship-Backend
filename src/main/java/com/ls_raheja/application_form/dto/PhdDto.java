package com.ls_raheja.application_form.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls_raheja.application_form.entity.PhdStatus;
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

    private int scopusIndexedPublications;
    private String scopusId;
    private Boolean presentedInConference;
    private int wosIndexedPublications;
    private String wosId;
}
