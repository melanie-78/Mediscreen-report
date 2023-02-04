package com.openclassrooms.mediscreenreport.proxies;

import com.openclassrooms.mediscreenreport.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="mediscreen-notes", url="localhost:8082")
public interface MicroserviceNotesProxy {
    @GetMapping(value = "/notes/{patId}")
    List<NoteBean> getNotesPatient(@PathVariable("patId") Integer patId);
}
