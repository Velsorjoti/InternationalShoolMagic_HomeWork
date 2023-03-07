package ru.magic.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magic.school.model.Student;
import ru.magic.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("student")
@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{idS}")
    public ResponseEntity getStudent(@PathVariable Long idS) {
        Student student = studentService.getStudentByIdS(idS);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping()
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student.getIdS(), student);
        return ResponseEntity.ok(updateStudent);
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> validStudentByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.getallStudent().stream().filter(student -> student.getAge() == age
        ).collect(Collectors.toList()));
    }
}
