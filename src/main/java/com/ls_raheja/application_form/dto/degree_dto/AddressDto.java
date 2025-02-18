package com.ls_raheja.application_form.dto.degree_dto;


import com.ls_raheja.application_form.entity.degree.States;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private States state;

    private String city;

    private String pinCode;

}
