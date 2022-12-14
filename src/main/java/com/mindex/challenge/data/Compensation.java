package com.mindex.challenge.data;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Compensation {
    private Employee employee;
    @NotNull(message = "Employee Salary should not be null")
    private Float salary;

    @NotNull(message = "Compensation Effective Date should not be null")
    @DateTimeFormat
    private Date effectiveDate;

    public Employee getEmployee() {
        return employee;
    }

    public Float getSalary() {
        return salary;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public Compensation() {
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
