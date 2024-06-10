package com.praveen.product_service.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAll-students")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentResponse>> getAllStudents() throws JsonProcessingException {
        List<StudentResponse> allStudentDetails = studentService.getAllStudentDetails();
        return new ResponseEntity<>(allStudentDetails,HttpStatus.OK);
    }

    @PostMapping("/craeteStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createStudent(@RequestBody StudentResponse student){
        ResponseEntity<StudentResponse> student1 = studentService.createStudent(student);
        HttpHeaders headers = student1.getHeaders();
        Map<String,String> map=new HashMap<>();
        map.put("body",student1.getBody().toString());
        map.put("headers",headers.toString());
        return student1;
    }

    @GetMapping("/getAccountBy/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getStudentById(@PathVariable String name){
        StudentResponse studentResponse = studentService.getstudentById(name);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @GetMapping("/getAccountByReqPerm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getStudentByIdRequestparam(@RequestParam String name){
        StudentResponse studentResponse = studentService.getstudentByIdReqParam(name);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }
}
