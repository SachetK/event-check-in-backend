package com.S3V.Event.Check.In.Tracker.controller;

import com.S3V.Event.Check.In.Tracker.model.Log;
import com.S3V.Event.Check.In.Tracker.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class LogController {
    @Autowired
    LogRepository logRepository;

    @GetMapping("/logs")
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    @PostMapping("/logs")
    public Log createSubject(@RequestBody Log log) {
        return logRepository.save(log);
    }
}
