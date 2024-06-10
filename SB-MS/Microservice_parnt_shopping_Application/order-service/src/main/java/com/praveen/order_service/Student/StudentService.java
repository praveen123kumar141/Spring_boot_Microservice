package com.praveen.order_service.Student;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    List<Student> listStudent=new ArrayList<>();

    public List<Student> loadData(){
       listStudent.add(new Student("praveen",25,"CSE","GUNTUR"));
        listStudent.add(new Student("jani",23,"BCOM","Tirupathi"));
        listStudent.add(new Student("niteesh",26,"ECE","nellore"));
        return listStudent;
    }

    public List<Student> getAllstudents(){
        return loadData();
    }

    public Student craeteStudent(Student s){
        boolean add = listStudent.add(s);
        if(add){
            //throw new RuntimeException();
           return s;
        }
        return null;
    }


    public Student getstudentById(String name) {
        List<Student> students = loadData();
        for(Student s:students)
            if(s.getName().equals(name))
                return s;
        return null;
    }

    public Student getstudentByIdReqParam(String name) {
        List<Student> students = loadData();
        for(Student s:students)
            if(s.getName().equals(name))
                return s;
        return null;
    }
}
