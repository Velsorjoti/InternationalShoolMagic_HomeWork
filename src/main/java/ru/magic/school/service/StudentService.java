package ru.magic.school.service;

import org.springframework.stereotype.Service;
import ru.magic.school.model.Student;
import ru.magic.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentByIdS(Long idS) {
        return studentRepository.findById(idS).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }


    public void deleteStudent(Long idS) {
        studentRepository.deleteById(idS);
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Collection<Student> validStudentByAge(Integer age) {
        return studentRepository.findAllByAge(age).stream().filter(student -> student.getAge() == age
        ).collect(Collectors.toList());
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min,max);
    }


}
