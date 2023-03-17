package ru.magic.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.magic.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentIdS(Long idS);

}
