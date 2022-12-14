package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Finding Reporting Structure of employeeId", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        // exclude the parent `employee` from the reports total as the helper method counts this employee + its direct reports
        int noOfReports = countAllEmployeesUnderAndInclusive(employee) - 1;

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(noOfReports);

        return reportingStructure;
    }

    private int countAllEmployeesUnderAndInclusive(Employee employee) {
        // include the current `employee` in the count for all the recursive calls
        int noOfEmployees = 1;
        List<Employee> listDirectReports = employee.getDirectReports();

        if (listDirectReports == null){
            return noOfEmployees;
        }

        // recursively call this method for all the employees in the list of direct reports
        for (Employee directReport : listDirectReports) {
            noOfEmployees += countAllEmployeesUnderAndInclusive(directReport);
        }

        return noOfEmployees;
    }



}
