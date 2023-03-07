package ru.magic.school.service;

import org.springframework.stereotype.Service;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class HouseService {
    private Map<Long, Faculty> facultys = new HashMap<>();
    private Long generatedFacultyIdF = 1L;



    public Faculty createFaculty(Faculty faculty) {
        facultys.put(generatedFacultyIdF, faculty);
        generatedFacultyIdF++;
        return faculty;
    }

    public Faculty getFacultyByIdF(Long idF) {
        return facultys.get(idF);
    }

    public Faculty updateFaculty(Long idF, Faculty faculty) {
        facultys.put(idF, faculty);
        return faculty;
    }


    public Faculty deleteFaculty(Long idF) {
        generatedFacultyIdF--;
        return facultys.remove(idF);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultys.values();
    }
}
