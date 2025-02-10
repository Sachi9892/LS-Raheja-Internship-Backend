package com.ls_raheja.application_form.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ls_raheja.application_form.entity.States;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private States state;

    private String city;

    private String pinCode;

}
