package ru.magic.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.service.HouseService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("faculty")
@RestController
public class HousesController {

    private HouseService houseService;

    public HousesController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty  = houseService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{idF}")
    public ResponseEntity getFaculty(@PathVariable Long  idF) {
        Faculty faculty = houseService.getFacultyByIdF(idF);
        if(faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = houseService.updateFaculty(faculty.getIdF(), faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @GetMapping("{color}")
    public ResponseEntity<Collection<Faculty>> validFacultyByColor (@PathVariable String color) {
        return ResponseEntity.ok(houseService.getAllFaculty().stream().filter(faculty -> faculty.getColor() == color
        ).collect(Collectors.toList()));
    }

}
