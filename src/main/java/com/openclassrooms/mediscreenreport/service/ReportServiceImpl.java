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

    @Override
    public Report getAssessmentByFamilyName(String patFirstName) {
        Report report = new Report();
        //return patient
        PatientBean patient = microservicePatientsProxy.getPatient(patFirstName);
        int age = calculateAge(patient.getBirthDate(), new Date());
        String assessment = determinatePatientRisk(patient.getId());

        report.setPatFirstName(patient.getFirstName());
        report.setPatLastName(patient.getLastName());
        report.setAge(age);
        report.setAssessment(assessment);

        return report;
    }


    public static int calculateAge(Date birthday, Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthday));
        int d2 = Integer.parseInt(formatter.format(date));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public int determinateTerminologyNumber(List<String> list) {
        //Create a table with terminology terms
        String[] terminology = new String[]{"Hemoglobin A1C", "Microalbumin", "Height", "Weight", "Smoker", "Abnormal", "Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies"};
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
        }else if (terminologyNumber == 1 && age > 30) {
            risk = "Borderline";
        }else if((gender.equals("M") && age < 30 && terminologyNumber == 3) || (gender.equals("F")  && age<30 && terminologyNumber==4) || (age>30 && terminologyNumber==6)){
            risk="In Danger";
        }else if((gender.equals("M")  && age < 30 && terminologyNumber >= 5) || (gender.equals("F")  && age<30 && terminologyNumber>=7) || (age>30 &&terminologyNumber>=6)){
            risk="Early onset";
        }
        return risk;
          }
        }
/*
        String risk = "None";

        switch (risk) {
            case "None":
                if (terminologyNumber == 0)
                    return "None";
                    break;
            case "Borderline":
                if (terminologyNumber == 2 && age > 30)
                    return "Borderline";
                    break;
            case "In Danger":
                if ((gender.equals("M") && age < 30 && terminologyNumber == 3) || (gender.equals("F") && age < 30 && terminologyNumber == 4) || (age > 30 && terminologyNumber == 6))
                    return "In Danger";
                    break;
            case "Early Onset":
                if ((gender.equals("M") && age < 30 && terminologyNumber >= 5) || (gender.equals("F") && age < 30 && terminologyNumber >= 7) || (age > 30 && terminologyNumber >= 6))
                    return "Early Onset";
                    break;
            default:
                return risk;
        }
        return risk;
    }
}*/
