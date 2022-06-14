package com.S3V.Event.Check.In.Tracker.controller;

import com.S3V.Event.Check.In.Tracker.model.Log;
import com.S3V.Event.Check.In.Tracker.repository.LogRepository;
import com.S3V.Event.Check.In.Tracker.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    @Autowired
    CSVService fileService;

    @Autowired
    LogRepository logRepository;

    @GetMapping
    public List<Log> getLogs() {
        List<Log> logs = logRepository.findAll();
        logs.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        return logs;
    }

    @DeleteMapping
    public void deleteAllLogs() {
        logRepository.deleteAll();
    }

    @PostMapping
    public Log createSubject(@RequestBody Log log) {
        return logRepository.save(log);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "logs.csv";
        InputStreamResource file = new InputStreamResource(fileService.load());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
