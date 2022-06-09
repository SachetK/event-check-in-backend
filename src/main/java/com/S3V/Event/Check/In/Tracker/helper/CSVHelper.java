package com.S3V.Event.Check.In.Tracker.helper;

import com.S3V.Event.Check.In.Tracker.model.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = { "ID", "Guest Ticket Number", "FIRST", "LAST", "MI", "Ticket", "GR", "Payment Method", "Guest YN" };
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
    public static List<Student> csvToStudents(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            List<Student> students = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Student tutorial = new Student(
                        !csvRecord.get("ID").equals("") ? Integer.parseInt(csvRecord.get("ID")) : null,
                        !csvRecord.get("Guest Ticket Number").equals("") ? Integer.parseInt(csvRecord.get("Guest Ticket Number")) : null,
                        csvRecord.get("FIRST"),
                        csvRecord.get("LAST"),
                        csvRecord.get("MI"),
                        Integer.parseInt(csvRecord.get("Ticket")),
                        !csvRecord.get("GR").equals("") ? Integer.parseInt(csvRecord.get("GR")) : null,
                        csvRecord.get("Payment Method"),
                        csvRecord.get("Guest YN"),
                        null);
                students.add(tutorial);
            }
            return students;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
