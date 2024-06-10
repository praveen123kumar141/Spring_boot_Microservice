package com.praveen.order_service.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {
    private String name;
    private int age;
    private String dept;
    private String adder;
}
