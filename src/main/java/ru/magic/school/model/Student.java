package ru.magic.school.model;

import java.util.Objects;

public class Student {
    private Long idS;
    private String name;
    private int age;

    public Student() {
    }

    public Student(Long idS, String name, int age) {
        this.idS = idS;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
