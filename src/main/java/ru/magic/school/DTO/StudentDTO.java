package ru.magic.school.DTO;

import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;

public class StudentDTO {
    private Long idS;
    private String name;
    private Integer age;
    private Long facultyId;

    public StudentDTO() {
    }

    public StudentDTO(Long idS, String name, Integer age, Long facultyId) {
        this.idS = idS;
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
    }

    public Long getIdS() {
        return idS;
    }

    public void setIdS(Long idS) {
        this.idS = idS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setIdS(student.getIdS());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFacultyId().getIdF());
        return dto;
    }

    public Student toStudent () {
        Student student = new Student();
        student.setIdS(this.getIdS());
        student.setName(this.getName());
        student.setAge(this.getAge());
        return student;
    }
}







