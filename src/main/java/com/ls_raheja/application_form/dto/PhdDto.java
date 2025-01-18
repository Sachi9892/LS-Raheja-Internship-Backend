package com.ls_raheja.application_form.dto;

import com.ls_raheja.application_form.entity.PhdStatus;
import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhdDto {

    private PhdStatus status;

    private String universityName;

    private LocalDate yearOfPassing;

    private int scopusIndexedPublications;
    private String scopusId;
    private boolean presentedInConference;
    private int wosIndexedPublications;
    private String wosId;
}
