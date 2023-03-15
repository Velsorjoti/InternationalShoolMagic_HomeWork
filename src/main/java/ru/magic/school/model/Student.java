package ru.magic.school.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idS;
    private String name;
    private Integer age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty facultyId;

    public Student() {
    }

    public Student(Long idS, String name, Integer age, Faculty facultyId) {
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

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(idS, student.idS) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idS, name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "idS=" + idS +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
