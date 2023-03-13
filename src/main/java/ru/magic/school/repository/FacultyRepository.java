package ru.magic.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.magic.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Collection<Faculty> findAllByColor(String color);
}
