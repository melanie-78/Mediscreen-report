package com.openclassrooms.mediscreenreport.proxies;

import com.openclassrooms.mediscreenreport.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="mediscreen", url="localhost:8080")
public interface MicroservicePatientsProxy {
    @GetMapping(value = "/patient/{id}")
    PatientBean getPatient(@PathVariable("id") Integer id);
}
