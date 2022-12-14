package com.mindex.challenge.data;

public class ReportingStructure {

    private Employee employee;
    private Integer numberOfReports;

    public ReportingStructure() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setNumberOfReports(Integer numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public Integer getNumberOfReports() {
        return numberOfReports;
    }
}
