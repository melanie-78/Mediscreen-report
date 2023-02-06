package com.openclassrooms.mediscreenreport.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
@NoArgsConstructor
public class PatientBean {
    private Integer id;
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @NotBlank(message = "birthDate is mandatory")
    private Date birthDate;
    @NotBlank(message = "Type is mandatory")
    private String type;
    private String address;
    private String number;
}
