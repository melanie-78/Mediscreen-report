package com.openclassrooms.mediscreenreport.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class Report {
    @NotBlank(message="patFirstName is mandatory")
    private String patFirstName;
    @NotBlank(message="patLastName is mandatory")
    private String patLastName;
    @NotBlank(message="assessment is mandatory")
    private String assessment;
    @NotBlank(message="age is mandatory")
    private int age;

    @Override
    public String toString() {
        return "Patient: " + patLastName +" "+ patFirstName + " (age " + age +") "+
                " diabetes assessment is: " + assessment;
    }
}
