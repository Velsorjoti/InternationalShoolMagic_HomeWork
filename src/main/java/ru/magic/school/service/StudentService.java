package ru.magic.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private FacultyRepository facultyRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = studentDTO.toStudent();
        student.setFacultyId(facultyRepository.findById(studentDTO.getFacultyId()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }

    public StudentDTO getStudentByIdS(Long idS) {
        return StudentDTO.fromStudent(studentRepository.findById(idS).orElse(null));
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = studentDTO.toStudent();
        student.setFacultyId(facultyRepository.findById(studentDTO.getFacultyId()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }


    public void deleteStudent(Long idS) {
        studentRepository.deleteById(idS);
    }

    public Collection<StudentDTO> getAllStudent() {
        return studentRepository.findAll().stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }



    public Collection<StudentDTO> validStudentByAge(Integer age) {
        return studentRepository.findAllByAge(age).stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public Collection<StudentDTO> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min, max).stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByIdStudent(Long idS) {
        Faculty faculty = facultyRepository.findById(getStudentByIdS(idS).getFacultyId()).get();
        return FacultyDTO.fromFaculty(faculty);
    }
}
