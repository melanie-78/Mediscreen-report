package com.openclassrooms.mediscreenreport.service;

import com.openclassrooms.mediscreenreport.beans.NoteBean;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
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

    @Test
    public void calculateAgeTest(){
        //GIVEN
        Date birthDate = new Date();

        //WHEN
        int age = reportService.calculateAge(birthDate, new Date());

        //THEN
        assertTrue(age==0);
    }

    @Test
    public void determinateTerminologyNumberTest(){
        //GIVEN
        List<String> list = new ArrayList<>();
        list.add("voici je vais mieux vu ma taille et mon poids");

        String[] terminology = new String[]{"hémoglobine A1C", "microalbumine", "taille", "poids", "fumeur", "anormal", "cholestérol", "vertige", "rechute", "réaction", "anticorps"};

        int expected = 2;
        //WHEN
        int actual = reportService.determinateTerminologyNumber(list);

        //THEN
        assertTrue(expected==actual);
    }

    @Test
    public void determinatePatientRiskTest(){
        //GIVEN
        Integer id=1;
        List<NoteBean> list = new ArrayList<>();
        NoteBean noteBean = new NoteBean();
        noteBean.setObservation("voici je vais mieux");

        PatientBean patientBean = new PatientBean();
        patientBean.setBirthDate(new Date());
        patientBean.setType("M");

        when(microservicePatientsProxy.getPatient(id)).thenReturn(patientBean);
        when(microserviceNotesProxy.getNotesPatient(id)).thenReturn(list);

        String expected = "None";

        //WHEN
        String actual = reportService.determinatePatientRisk(id);

        //THEN
        assertEquals(expected, actual);

    }

    @Test
    public void getAssessmentByIdTest(){
        //GIVEN
        Integer id = 1;
        PatientBean patientBean = new PatientBean();
        patientBean.setBirthDate(new Date());
        patientBean.setFirstName("ADJOH");
        patientBean.setLastName("Melanie");
        patientBean.setType("M");

        List<NoteBean> list = new ArrayList<>();
        NoteBean noteBean = new NoteBean();
        noteBean.setObservation("voici je vais mieux");

        when(microservicePatientsProxy.getPatient(id)).thenReturn(patientBean);
        when(microserviceNotesProxy.getNotesPatient(id)).thenReturn(list);

        Report expected = new Report("ADJOH", "Melanie","none",0);


        //WHEN
        Report actual= reportService.getAssessmentById(id);

        //THEN
        verify(microservicePatientsProxy, Mockito.times(2)).getPatient(id);
        verify(microserviceNotesProxy, Mockito.times(1)).getNotesPatient(id);
        assertEquals(expected.getPatFirstName(), actual.getPatFirstName());

    }
}
