package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String employeeIdUrl;
    private Compensation expectedCompensation = new Compensation();


    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() throws Exception{
         // define url requests
        compensationUrl = "http://localhost:" + port + "/compensation";
        employeeIdUrl = "http://localhost:" + port + "/compensation/{id}";

        // creating an employee setup
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        // setting up an existing employee to compensation
        expectedCompensation.setEmployee(testEmployee);
        expectedCompensation.setSalary(4500.50F);

        //using date parser to set a proper date format
        SimpleDateFormat dateParser = new SimpleDateFormat ("MM/dd/yyyy"); //Format for input
        String date= "12/21/2022";
        expectedCompensation.setEffectiveDate(dateParser.parse(date));
    }

    @Test
    public void testCreate() {
        //Make an API request using parameters in setup
        Compensation actualCreateCompensation = restTemplate.postForEntity(compensationUrl, expectedCompensation, Compensation.class).getBody();
        //Check assertions for create request
        assertEquals(expectedCompensation.getSalary(), actualCreateCompensation.getSalary());
        assertEquals(expectedCompensation.getEffectiveDate(), actualCreateCompensation.getEffectiveDate());

    }

    @Test
        public void testRead() {
        //Make an API request using parameters in setup
        Compensation readEmpCompensation = restTemplate.getForEntity(employeeIdUrl, Compensation.class, expectedCompensation.getEmployee().getEmployeeId()).getBody();
        //Check assertions for read request
        assertEquals(expectedCompensation.getSalary(), readEmpCompensation.getSalary());
        assertEquals(expectedCompensation.getEffectiveDate(), readEmpCompensation.getEffectiveDate());
//        cleanUpTestData(expectedCompensation.getEmployee().getEmployeeId());
    }
    
    private void cleanUpTestData(String id){
        //cleaning up after testing create (don't want duplicate employee ids)
        String cleanUpUrl = "http://localhost:" + port + "/compensation/{id}";
        restTemplate.delete(cleanUpUrl, Compensation.class, id);

    }
}