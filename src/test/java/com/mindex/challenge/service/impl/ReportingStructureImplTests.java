package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureImplTests {

    private String reportingStructureEmpIdUrl;
    private Employee testEmployee0;
    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureEmpIdUrl = "http://localhost:" + port + "/reporting_structure/{id}";


        //initialize an employee
        testEmployee0 = new Employee();
        testEmployee0.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee0.setFirstName("John");
        testEmployee0.setLastName("Doe");
        testEmployee0.setDepartment("Engineering");
        testEmployee0.setPosition("Sr. Developer");

        //initialize other employees that will report to emp 0
        Employee testEmployee1 = new Employee();
        testEmployee1.setFirstName("Paul");
        testEmployee1.setLastName("McCartney");
        testEmployee1.setDepartment("Engineering");
        testEmployee1.setPosition("Jr. Developer");

        Employee testEmployee2 = new Employee();
        testEmployee2.setFirstName("Ringo");
        testEmployee2.setLastName("Start");
        testEmployee2.setDepartment("Engineering");
        testEmployee2.setPosition("Developer");

        List<Employee> directReportListEmp0 = new ArrayList<>();
        directReportListEmp0.add(testEmployee2);
        directReportListEmp0.add(testEmployee1);
        testEmployee0.setDirectReports(directReportListEmp0);

    }

    @Test
    public void testRead() {
        ReportingStructure expectedReportStrut = new ReportingStructure();
        expectedReportStrut.setEmployee(testEmployee0);
        expectedReportStrut.setNumberOfReports(2);

        //Make an API request using parameters in setup
        ReportingStructure actualReportStrut = restTemplate.getForEntity(reportingStructureEmpIdUrl,
                ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();

        //Check for read assertions
        assertEquals(expectedReportStrut.getNumberOfReports(), actualReportStrut.getNumberOfReports());
    }
}
