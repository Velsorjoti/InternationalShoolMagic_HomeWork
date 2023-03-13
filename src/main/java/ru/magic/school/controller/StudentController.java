package ru.magic.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/student")
@RestController
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{idS}")
    public ResponseEntity<Student> getStudent(@PathVariable Long idS) {
        Student student = studentService.getStudentByIdS(idS);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PatchMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
       Student updateStudent = studentService.updateStudent(student);
       if (updateStudent == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("{idS}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long idS) {
        studentService.deleteStudent(idS);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> validStudentByAge(@RequestParam Integer age) {
        return ResponseEntity.ok(studentService.validStudentByAge(age));
    }

}
