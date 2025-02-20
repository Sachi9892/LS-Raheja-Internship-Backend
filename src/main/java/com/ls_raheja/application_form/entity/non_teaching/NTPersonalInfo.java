package com.ls_raheja.application_form.entity.non_teaching;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "NT_Info")
@Getter
@Setter
public class NTPersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long infoId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNumber;

    private String address;

    private String dob;

}
