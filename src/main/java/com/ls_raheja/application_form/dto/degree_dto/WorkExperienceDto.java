package com.ls_raheja.application_form.dto.degree_dto;


import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkExperienceDto {

    private Boolean isFresher;
    private List<WorkExperienceDetailDto> list = new ArrayList<>();

}
