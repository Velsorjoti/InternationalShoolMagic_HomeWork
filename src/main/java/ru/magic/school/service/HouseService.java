package ru.magic.school.service;

import org.springframework.stereotype.Service;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HouseService {

    private FacultyRepository facultyRepository;
    private StudentRepository studentRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty = facultyDTO.toFaculty();
        Faculty faculty1 = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(faculty1);
    }

    public FacultyDTO getFacultyByIdF(Long idF) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(idF).orElse(null));
    }

    public FacultyDTO findByNameIgnoreCase(String name) {
        return FacultyDTO.fromFaculty(facultyRepository.findByNameIgnoreCase(name));
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty = facultyDTO.toFaculty();
        Faculty faculty1 = facultyRepository.save(faculty);
        return FacultyDTO.fromFaculty(faculty1);
    }


    public void deleteFaculty(Long idF) {
        facultyRepository.deleteById(idF);
    }

    public Collection<FacultyDTO> getAllFaculty() {
        return facultyRepository.findAll().stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<FacultyDTO> validFacultyByColor(String color) {
        return  facultyRepository.findAll().stream().filter(faculty -> faculty.getColor() == color).collect(Collectors.toList())
                .stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<StudentDTO> getAllStudentsByFacultyId(Long idF) {
        List<Student> students = facultyRepository.findById(idF).get().getStudentList();
        List<StudentDTO> studentsDTO = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = StudentDTO.fromStudent(student);
            studentsDTO.add(studentDTO);
        }
        return studentsDTO;
    }
}
