package ru.magic.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.magic.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
