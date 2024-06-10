package com.praveen.product_service.student;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class StudentResponse {
    private String name;
    private int age;
    private String dept;
    private String adder;
}
