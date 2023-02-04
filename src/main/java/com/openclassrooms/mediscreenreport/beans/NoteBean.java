package com.openclassrooms.mediscreenreport.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteBean {
    private String id;
    private Integer patId;
    private String patName;
    private String observation;
}
