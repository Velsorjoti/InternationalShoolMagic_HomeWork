package ru.magic.school.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idF;
    private String name;
    private String color;

    public Faculty() {
    }

    public Faculty(Long idF, String name, String color) {
        this.idF = idF;
        this.name = name;
        this.color = color;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(idF, faculty.idF) && Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idF, name, color);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "idF=" + idF +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
