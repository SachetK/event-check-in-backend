package com.S3V.Event.Check.In.Tracker.repository;

import com.S3V.Event.Check.In.Tracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}