package ru.magic.school.service;

import org.springframework.stereotype.Service;
import ru.magic.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private Long generatedUserIdS = 1L;

    public Student createStudent(Student student) {
        students.put(generatedUserIdS, student);
        generatedUserIdS++;
        return student;
    }

    public Student getStudentByIdS(Long idS) {
        return students.get(idS);
    }

    public Student updateStudent(Long idS, Student student) {
        students.put(idS, student);
        return student;
    }


    public Student deleteStudent(Long idS) {
        generatedUserIdS--;
        return students.remove(idS);
    }

    public Collection<Student> getallStudent() {
        return students.values();
    }
}
