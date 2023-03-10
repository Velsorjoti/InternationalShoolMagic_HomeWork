package ru.magic.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.magic.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
