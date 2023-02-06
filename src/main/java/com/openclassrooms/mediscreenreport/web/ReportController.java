package com.openclassrooms.mediscreenreport.web;

import com.openclassrooms.mediscreenreport.dto.Report;
import com.openclassrooms.mediscreenreport.proxies.MicroservicePatientsProxy;
import com.openclassrooms.mediscreenreport.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ReportController {
    private ReportService reportService;
    private final MicroservicePatientsProxy microservicePatientsProxy;

    @GetMapping("/assess/patId/{patId}")
    public Report getAssessmentById(@PathVariable("patId") Integer patId){
        Report assessmentById = reportService.getAssessmentById(patId);
        return assessmentById;
    }

}
