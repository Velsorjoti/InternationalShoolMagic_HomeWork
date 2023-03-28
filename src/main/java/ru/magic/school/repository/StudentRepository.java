package ru.magic.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.magic.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public Collection<Student> findByAgeBetween(Integer min, Integer max);
    public Collection<Student> findAllByAge(Integer age);

    @Query(value = "SELECT COUNT(*) AS count FROM student AS s", nativeQuery = true)
    Long getAllStudentCount();

    @Query(value = "SELECT AVG(s.age) FROM student AS s", nativeQuery = true)
    Long getAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY age LIMIT 5", nativeQuery = true)
    Collection<Student> getFirstFiveYoung();
}
