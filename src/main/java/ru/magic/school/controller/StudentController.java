package ru.magic.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.service.HouseService;
import ru.magic.school.service.StudentService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/student")
@RestController
public class StudentController {
    private StudentService studentService;
    private HouseService houseService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{idS}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long idS) {
        StudentDTO studentDTO = studentService.getStudentByIdS(idS);
        if(studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTO);
    }

    @PatchMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
       StudentDTO updateStudentDTO = studentService.updateStudent(studentDTO);
       if (updateStudentDTO == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       return ResponseEntity.ok(updateStudentDTO);
    }

    @DeleteMapping("{idS}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long idS) {
        studentService.deleteStudent(idS);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> validStudentByAge(@RequestParam Integer age) {
        return ResponseEntity.ok(studentService.validStudentByAge(age));
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> findByAgeBetween(@RequestParam Integer min,@RequestParam Integer max) {
       return  ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("{idS}")
    public ResponseEntity<FacultyDTO> getFacultyByIdStudent(@PathVariable Long idS) {
        return ResponseEntity.ok(studentService.getFacultyByIdStudent(idS));
    }

}
