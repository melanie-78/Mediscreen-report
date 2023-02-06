package com.openclassrooms.mediscreenreport.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class NoteBean {
    private String id;
    @NotBlank(message = "patId is mandatory")
    private Integer patId;
    @NotBlank(message = "patName is mandatory")
    private String patName;
    @NotBlank(message = "observation is mandatory")
    private String observation;
}
