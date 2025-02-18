package com.ls_raheja.application_form.dto.non_teaching;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NTAdditionalInfoDTO {

    private Integer ets;
    private Integer mts;
    private String motherTounge;
    private String otherLanguage;
    private Integer joinDate;
    private Double exceptedSalary;
    private String comment;
}
