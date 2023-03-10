package ru.magic.school.service;

import org.springframework.stereotype.Service;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class HouseService {

    private FacultyRepository facultyRepository;

    public HouseService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyByIdF(Long idF) {
        return facultyRepository.findById(idF).orElse(null);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }


    public void deleteFaculty(Long idF) {
        facultyRepository.deleteById(idF);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}
