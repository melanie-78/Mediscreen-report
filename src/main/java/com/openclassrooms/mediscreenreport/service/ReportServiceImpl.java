package com.openclassrooms.mediscreenreport.service;

import com.openclassrooms.mediscreenreport.beans.PatientBean;
import com.openclassrooms.mediscreenreport.dto.Report;
import com.openclassrooms.mediscreenreport.proxies.MicroserviceNotesProxy;
import com.openclassrooms.mediscreenreport.proxies.MicroservicePatientsProxy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final MicroserviceNotesProxy microserviceNotesProxy;
    private final MicroservicePatientsProxy microservicePatientsProxy;

    /**
     *
     * @param id the identifier of a patient saved in microservice in charge of patient data management
     * @return a report with assessment's degree to risk contracting diabetes
     */
    @Override
    public Report getAssessmentById(Integer id) {
        Report report = new Report();
        //return patient
        PatientBean patient = microservicePatientsProxy.getPatient(id);
        int age = calculateAge(patient.getBirthDate(), new Date());
        String assessment = determinatePatientRisk(id);


        report.setPatFirstName(patient.getFirstName());
        report.setPatLastName(patient.getLastName());
        report.setAge(age);
        report.setAssessment(assessment);

        return report;
    }


    /**
     *
     * @param birthday the patient's birthdate
     * @param date today
     * @return age of the patient
     */
    public int calculateAge(Date birthday, Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthday));
        int d2 = Integer.parseInt(formatter.format(date));
        int age = (d2 - d1) / 10000;
        return age;
    }

    /**
     *
     * @param list of observations made by a doctor about a patient
     * @return times different terminology's terms appears
     */
    public int determinateTerminologyNumber(List<String> list) {
        //Create a table with terminology terms
        String[] terminology = new String[]{"hémoglobine A1C", "microalbumine", "taille", "poids", "fumeur", "anormal", "cholestérol", "vertige", "rechute", "réaction", "anticorps"};
        int number = 0;
        for(String element : list) {
            for (int i = 0; i < terminology.length; i++) {
                if (element.contains(terminology[i])) {
                    number = number + 1;
                }
            }
        }
        return number;
    }

    /**
     *
     * @param id the identifier of a patient
     * @return the risk associated to the patient
     */
    public String determinatePatientRisk(Integer id) {

        //return patient
        PatientBean patient = microservicePatientsProxy.getPatient(id);
        //return age of patient
        int age = calculateAge(patient.getBirthDate(), new Date());
        //return sex of patient
        String gender = patient.getType();
        //return a list of patient's observation
        List<String> observations = microserviceNotesProxy.getNotesPatient(id)
                .stream()
                .map(noteBean -> {
                    String observation = noteBean.getObservation();
                    return observation;
                }).collect(Collectors.toList());

        int terminologyNumber = determinateTerminologyNumber(observations);

        String risk="None";

        if (terminologyNumber == 0) {
            risk = "None";
        }else if (terminologyNumber >= 2 && terminologyNumber <6 && age > 30) {
            risk = "Borderline";
        }else if((gender.equals("M") && age < 30 && terminologyNumber == 3) || (gender.equals("F")  && age<30 && terminologyNumber==4) || (age>30 && terminologyNumber==6)){
            risk="In Danger";
        }else if((gender.equals("M")  && age < 30 && terminologyNumber >= 5) || (gender.equals("F")  && age<30 && terminologyNumber>=7) || (age>30 &&terminologyNumber>=6)){
            risk="Early onset";
        }
        return risk;
          }
        }
