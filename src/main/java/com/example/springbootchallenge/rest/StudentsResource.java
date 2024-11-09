package com.example.springbootchallenge.rest;

import com.example.springbootchallenge.rest.dto.Student;
import com.example.springbootchallenge.rest.dto.StudentsDetails;
import com.example.springbootchallenge.service.TestService;
import com.example.springbootchallenge.service.intf.GreetingIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentsResource {

    @PostMapping("/upload")
    public ResponseEntity<String>
    handleFileUpload(@RequestParam("file") MultipartFile file) {
        // Logic to handle file upload
        return (ResponseEntity<String>)
                ResponseEntity.status(HttpStatus.CREATED);
    }


    @Autowired
    @Lazy
    private GreetingIntf greetingIntf;


    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String handle(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }
    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        if(student == null
                || student.getId() == null
                || student.getName() == null
                || student.getPhoneNo() == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        } else {
            student.setId("1"); // Dummy ID for example
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }
        return null;
    }

    @GetMapping("/names")
    public List<String> getAllStudentsNames() {
        greetingIntf.greet();
        List<String> studentList =  new ArrayList<>();
        studentList.add("Sunchit");
        studentList.add("Suraj");
        studentList.add("Shalini");
        studentList.add("Saurabh");
        return studentList;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id, @RequestHeader Map<String, String> headers) {

        if((headers != null) && (Integer.valueOf(headers.get("version")) < 1)){
            return new ResponseEntity<>(HttpStatus.GONE);
        }

        if(id == 2024L){
            Student student =  new Student();
            student.setId(String.valueOf(id));
            student.setName("Sunchit Dudeja");
            student.setPhoneNo("999XXXXYYYY");
            return ResponseEntity.ok()
                    .header("x-request-id", UUID.randomUUID().toString())
                    .body(student);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/data")
    public ResponseEntity<Student> getStudentByIdQueryParam(@RequestParam("id") long id) {
        if(id == 2024L){
            Student student =  new Student();
            student.setId(String.valueOf(id));
            student.setName("Sunchit Dudeja");
            student.setPhoneNo("999XXXXYYYY");
            return ResponseEntity.ok()
                    .header("x-request-id", UUID.randomUUID().toString())
                    .body(student);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/details")
    public StudentsDetails getStudentsDetails() {
        StudentsDetails studentsDetails  = new StudentsDetails();
        Student student = new Student();
        student.setName("Sunchit Dudeja");
        student.setId("20241");
        student.setPhoneNo("999XXYYYZZ");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentsDetails.setStudents(studentList);
        return studentsDetails;

    }

    @GetMapping(value = "/instructor", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getStudentsInstructor() {
        return "Hi! This is Sunchit Dudeja!";
    }

    @GetMapping(value = "/test")
    public String getStudentsMarks() {
        return testService.getDBUrl();

    }


    @GetMapping(value = "/landingpage", produces = MediaType.TEXT_HTML_VALUE)
    public String getStudentsLandingpage() {
        return "<html><body><h1>Welcome to Spring Boot Series by Coding Decoded!</h1></body></html>";
    }





}
