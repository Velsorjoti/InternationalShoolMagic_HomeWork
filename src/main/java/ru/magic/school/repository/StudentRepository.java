package ru.magic.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.magic.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Collection<Student> findByAgeBetween(Integer min, Integer max);
    public Collection<Student> findAllByAge(Integer age);
}
