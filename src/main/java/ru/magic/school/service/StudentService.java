package ru.magic.school.service;

import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;
    private FacultyRepository facultyRepository;

    public static final Logger logger = LoggerFactory.getLogger(StudentService.class);


    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }


    public StudentDTO createStudent(StudentDTO studentDTO) {
        logger.info("There is a new wonderful student, meow and smack.");
        Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId()).get();
        Student student = studentDTO.toStudent();
        student.setFacultyId(faculty);
        Student student1 = studentRepository.save(student);
        return StudentDTO.fromStudent(studentRepository.save(student1));
    }

    public StudentDTO getStudentByIdS(Long idS) {
        logger.info("Show student with id:" + idS);
        return StudentDTO.fromStudent(studentRepository.findById(idS).orElse(null));
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        logger.warn("Changing the student");
        Student student = studentDTO.toStudent();
        student.setFacultyId(facultyRepository.findById(studentDTO.getFacultyId()).get());
        return StudentDTO.fromStudent(studentRepository.save(student));
    }


    public void deleteStudent(Long idS) {
        logger.warn("Deleting a student with id:" + idS);
        studentRepository.deleteById(idS);
    }

    public Collection<StudentDTO> getAllStudent(Integer page, Integer pageSize) {
        logger.info("Show all students");
        PageRequest pageRequest = PageRequest.of((page - 1), pageSize);
        return studentRepository.findAll(pageRequest).getContent().stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }



    public Collection<StudentDTO> validStudentByAge(Integer age) {
        logger.info("We check the student's " + age + " for compliance.");
        return studentRepository.findAllByAge(age).stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public Collection<StudentDTO> findByAgeBetween(Integer min, Integer max) {
        logger.info("Founded all students beetween " + min + " and " + max + " age.");
        return studentRepository.findByAgeBetween(min, max).stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

    public FacultyDTO getFacultyByIdStudent(Long idS) {
        logger.info("Show all faculties with student_id:" + idS);
        Faculty faculty = facultyRepository.findById(getStudentByIdS(idS).getFacultyId()).get();
        return FacultyDTO.fromFaculty(faculty);
    }

    public Long getAllStudentCount() {
        logger.info("We get the number of all students in the school");
        return studentRepository.getAllStudentCount();
    }

    public Long getAverageAge() {
        logger.info("We get the average age of students");
        return studentRepository.getAverageAge();
    }

    public Collection<StudentDTO> getFirstFiveYoungStudent() {
        logger.info("We get the five youngest students.");
        return studentRepository.getFirstFiveYoung().stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
    }

}
