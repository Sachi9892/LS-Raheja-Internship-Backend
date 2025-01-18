package com.ls_raheja.application_form.dto;


import com.ls_raheja.application_form.entity.States;
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
