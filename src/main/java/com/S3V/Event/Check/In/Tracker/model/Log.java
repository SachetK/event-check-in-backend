package com.S3V.Event.Check.In.Tracker.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "logger")
    @NotBlank
    private String logger;

    @Column(name = "message")
    @NotBlank
    private String message;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Log() {

    }

    public Log(long id, String logger, String message) {
        this.id = id;
        this.logger = logger;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
