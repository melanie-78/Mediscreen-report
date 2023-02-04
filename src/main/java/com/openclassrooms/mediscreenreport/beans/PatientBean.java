package com.openclassrooms.mediscreenreport.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class PatientBean {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String type;
    private String address;
    private String number;
}
