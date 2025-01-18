package com.ls_raheja.application_form.dto;


import lombok.*;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkExperienceDto {

    private boolean isFresher;
    private List<WorkExperienceDetailDto> list;

}
