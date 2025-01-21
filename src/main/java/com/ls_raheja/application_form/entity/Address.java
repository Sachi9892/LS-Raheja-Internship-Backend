package com.ls_raheja.application_form.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @Enumerated(EnumType.STRING)
    private States state;

    private String city;

    private String pinCode;
    
}
