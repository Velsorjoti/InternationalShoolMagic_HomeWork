package ru.magic.school.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idF;
    private String name;
    private String color;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private List<Student> studentList;

    public Faculty() {
    }

    public Faculty(Long idF, String name, String color, List<Student> studentList) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(idF, faculty.idF) && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color) && Objects.equals(studentList, faculty.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idF, name, color, studentList);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "idF=" + idF +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
