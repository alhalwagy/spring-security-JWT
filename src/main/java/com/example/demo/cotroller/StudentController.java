package com.example.demo.cotroller;


import com.example.demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  @GetMapping("/")
  public String sayHello(){
    return "Hello World";
  }

List<Student>  students =new ArrayList<Student>(List.of(new Student(1,"ahmed","js"),new Student(2,"hlwg","java")));

  @GetMapping("students")
  public List<Student> getStudents(){
    return students;
  }



}
