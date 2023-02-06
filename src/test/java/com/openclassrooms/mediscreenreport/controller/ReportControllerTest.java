package com.openclassrooms.mediscreenreport.controller;

import com.openclassrooms.mediscreenreport.dto.Report;
import com.openclassrooms.mediscreenreport.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Test
    public void testGetAssessmentById() throws Exception {
        Integer patId = 1;
        Report report = new Report();
        when(reportService.getAssessmentById(patId)).thenReturn(report);

        this.mockMvc
                .perform(get("/assess/patId/"+ patId))
                .andExpect(status().isOk());
    }
}
