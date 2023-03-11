package ru.magic.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.service.HouseService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/faculty")
@RestController
public class HousesController {

    private HouseService houseService;

    public HousesController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty  = houseService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{idF}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long  idF) {
        Faculty faculty = houseService.getFacultyByIdF(idF);
        if(faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PatchMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updateFaculty = houseService.updateFaculty(faculty);
        if (updateFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateFaculty);
    }

    @GetMapping
    public ResponseEntity<Faculty> findByNameIgnoreCase(@RequestParam String name) {
        Faculty foundedFaculty = houseService.findByNameIgnoreCase(name);
        if(foundedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundedFaculty);
    }

    @DeleteMapping("{idF}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long idF) {
        houseService.deleteFaculty(idF);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> validFacultyByColor (@RequestParam String color) {
        return ResponseEntity.ok(houseService.validFacultyByColor(color));
    }

}
