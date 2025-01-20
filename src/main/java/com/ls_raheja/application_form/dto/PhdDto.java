package com.ls_raheja.application_form.dto;

import com.ls_raheja.application_form.entity.PhdStatus;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhdDto {

    private PhdStatus status;

    private String universityName;

    private String yearOfPassing;

    private int scopusIndexedPublications;
    private String scopusId;
    private Boolean presentedInConference;
    private int wosIndexedPublications;
    private String wosId;
}
