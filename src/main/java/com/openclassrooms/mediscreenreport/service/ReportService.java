package com.openclassrooms.mediscreenreport.service;

import com.openclassrooms.mediscreenreport.dto.Report;

public interface ReportService {
    Report getAssessmentById(Integer Id);
}
