package com.praveen.order_service.Student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getAllStudents")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllstudents(){
        log.info("enter into the order service  controller");
        List<Student> allstudents = studentService.getAllstudents();
        return allstudents;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student s){
        Student student = studentService.craeteStudent(s);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }
    @GetMapping("/getAccountBy/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> getStudentById(@PathVariable String name){
        Student studentResponse = studentService.getstudentById(name);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }

    @GetMapping("/getAccountByReqPerm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> getStudentByIdRequestparam(@RequestParam String name){
        Student studentResponse = studentService.getstudentByIdReqParam(name);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);
    }
}
