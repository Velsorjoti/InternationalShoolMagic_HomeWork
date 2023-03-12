package ru.magic.school.DTO;

import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FacultyDTO {
    private Long idF;
    private String name;
    private String color;
    private List<Student> studentList;

    public FacultyDTO() {
    }

    public FacultyDTO(Long idF, String name, String color, List<Student> studentList) {
        this.idF = idF;
        this.name = name;
        this.color = color;
        this.studentList = studentList;
    }

    public Long getIdF() {
        return idF;
    }

    public void setIdF(Long idF) {
        this.idF = idF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public static FacultyDTO fromFaculty(Faculty faculty) {
        FacultyDTO dto = new FacultyDTO();
        dto.setIdF(faculty.getIdF());
        dto.setName(faculty.getName());
        dto.setColor(faculty.getColor());
        return dto;
    }

    public Faculty toFaculty() {
        Faculty faculty = new Faculty();
        faculty.setIdF(this.getIdF());
        faculty.setName(this.getName());
        faculty.setColor(this.getColor());
        return faculty;
    }



}
