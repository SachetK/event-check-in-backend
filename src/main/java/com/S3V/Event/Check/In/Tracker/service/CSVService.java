package com.S3V.Event.Check.In.Tracker.service;

import com.S3V.Event.Check.In.Tracker.helper.CSVHelper;
import com.S3V.Event.Check.In.Tracker.model.Log;
import com.S3V.Event.Check.In.Tracker.model.Student;
import com.S3V.Event.Check.In.Tracker.repository.LogRepository;
import com.S3V.Event.Check.In.Tracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

// Creates a Service Bean for Spring Boot to use to work with CSV

@Service
public class CSVService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private LogRepository logRepository;

    public void save(MultipartFile file) {
        try {
            List<Student> tutorials = CSVHelper.csvToStudents(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Log> logs = logRepository.findAll();
        ByteArrayInputStream in = CSVHelper.logsToCSV(logs);
        return in;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }
}
