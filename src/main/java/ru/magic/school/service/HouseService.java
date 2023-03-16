package ru.magic.school.service;

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

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public FacultyDTO createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
    }

    public FacultyDTO getFacultyByIdF(Long idF) {
        return FacultyDTO.fromFaculty(facultyRepository.findById(idF).orElse(null));
    }

    public Collection<FacultyDTO> findByNameIgnoreCase(String name) {
        return facultyRepository.findAllByColor(name).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyDTO.toFaculty();
        return FacultyDTO.fromFaculty(facultyRepository.save(faculty));
    }


    public void deleteFaculty(Long idF) {
        facultyRepository.deleteById(idF);
    }

    public Collection<FacultyDTO> getAllFaculty() {
        return facultyRepository.findAll().stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
    }

    public Collection<FacultyDTO> validFacultyByColor(String color) {
        return  facultyRepository.findAllByColor(color).stream().map(FacultyDTO::fromFaculty).collect(Collectors.toList());
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
