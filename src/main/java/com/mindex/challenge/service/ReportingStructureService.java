package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportingStructureService {
    ReportingStructure read(String id);

}
