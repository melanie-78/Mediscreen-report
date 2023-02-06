package com.openclassrooms.mediscreenreport.service;

import com.openclassrooms.mediscreenreport.beans.PatientBean;
import com.openclassrooms.mediscreenreport.dto.Report;
import com.openclassrooms.mediscreenreport.proxies.MicroserviceNotesProxy;
import com.openclassrooms.mediscreenreport.proxies.MicroservicePatientsProxy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
    @InjectMocks
    private ReportServiceImpl reportService;

    @Mock
    private MicroserviceNotesProxy microserviceNotesProxy;

    @Mock
    private MicroservicePatientsProxy microservicePatientsProxy;


    //@Test
    public void getAssessmentByIdTest(){
        //GIVEN
        Integer id = 1;
        PatientBean patientBean = new PatientBean();

        when(microservicePatientsProxy.getPatient(id)).thenReturn(patientBean);

        //WHEN
        Report actual= reportService.getAssessmentById(id);

        //THEN
        verify(microservicePatientsProxy, Mockito.times(1)).getPatient(id);
    }
}
