package com.ls_raheja.application_form.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Embeddable
@Builder
@Data
public class Address implements Serializable {

    @Enumerated(EnumType.STRING)
    private States state;

    private String city;

    private String pinCode;
}
