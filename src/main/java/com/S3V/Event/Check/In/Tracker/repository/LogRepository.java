package com.S3V.Event.Check.In.Tracker.repository;

import com.S3V.Event.Check.In.Tracker.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}
