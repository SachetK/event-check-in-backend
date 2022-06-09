package com.S3V.Event.Check.In.Tracker.controller;

import com.S3V.Event.Check.In.Tracker.helper.CSVHelper;
import com.S3V.Event.Check.In.Tracker.message.ResponseMessage;
import com.S3V.Event.Check.In.Tracker.model.Student;
import com.S3V.Event.Check.In.Tracker.repository.StudentRepository;
import com.S3V.Event.Check.In.Tracker.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@CrossOrigin(origins = "https://event-check-in-tracker.herokuapp.com")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
    @Autowired
    private CSVService fileService;

    @Autowired
    private StudentRepository studentRepository;

    // get all people
    @GetMapping("/people")
    public List<Student> getAllPeople(){
        return studentRepository.findAll();
    }

    @DeleteMapping("/people")
    public void deleteAllPeople() {
        studentRepository.deleteAll();
    }

    @PostMapping("/uploads")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PutMapping("/people/{studentId}")
    public void checkInPerson(@PathVariable long studentId){
        Student student = studentRepository.findById(studentId).get();
        student.setChecked(true);
    }
}