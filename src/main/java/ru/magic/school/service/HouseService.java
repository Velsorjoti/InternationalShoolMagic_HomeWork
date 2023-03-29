package ru.magic.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class HouseService {

    private FacultyRepository facultyRepository;

    public static final Logger logger = LoggerFactory.getLogger(HouseService.class);

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        logger.info("A new wonderful faculty has been created, meow and smack.");
        Faculty faculty = facultyDTO.toFaculty();
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
    }

    public FacultyDTO getFacultyByIdF(Long idF) {
        logger.info("Show faculty with id:" + idF);
        return FacultyDTO.fromFaculty(facultyRepository.findById(idF).orElse(null));
    }

    public Collection<FacultyDTO> findByNameIgnoreCase(String name) {
        logger.info("Founded faculty with name:" + name);
        return facultyRepository.findByNameIgnoreCase(name).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        logger.warn("Faculty renewal");
        Faculty faculty = facultyDTO.toFaculty();
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
    }


    public void deleteFaculty(Long idF) {
        logger.warn("Deleting a faculty with id:" + idF);
        facultyRepository.deleteById(idF);
    }

    public Collection<FacultyDTO> getAllFaculty() {
        logger.info("Show all faculties");
        return facultyRepository.findAll().stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<FacultyDTO> validFacultyByColor(String color) {
        logger.info("We check whether the color of the faculty corresponds to the " + color + " color.");
        return  facultyRepository.findAllByColor(color).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<StudentDTO> getAllStudentsByFacultyId(Long idF) {
        logger.info("Show all students with faculty_id:" + idF);
        List<Student> students = facultyRepository.findById(idF).get().getStudentList();
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }
}
