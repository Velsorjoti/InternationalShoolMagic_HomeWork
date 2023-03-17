package ru.magic.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
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
    public ResponseEntity<FacultyDTO> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO createdFaculty  = houseService.createFaculty(facultyDTO);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{idF}")
    public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long  idF) {
        FacultyDTO facultyDTO = houseService.getFacultyByIdF(idF);
        if(facultyDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyDTO);
    }

    @PatchMapping
    public ResponseEntity<FacultyDTO> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO updateFacultyDTO = houseService.updateFaculty(facultyDTO);
        if (updateFacultyDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updateFacultyDTO);
    }

    @GetMapping
    public ResponseEntity<Collection<FacultyDTO>> getFaculty(@RequestParam(required = false) String name,@RequestParam(required = false) String color) {
        if(name != null && !name.isBlank()) {
            return ResponseEntity.ok(houseService.findByNameIgnoreCase(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(houseService.validFacultyByColor(color));
        }
        return ResponseEntity.ok(houseService.getAllFaculty());
    }

    @DeleteMapping("{idF}")
    public ResponseEntity<FacultyDTO> deleteFaculty(@PathVariable Long idF) {
        houseService.deleteFaculty(idF);
        return ResponseEntity.ok().build();
    }


    @GetMapping("{idF}/student")
    public ResponseEntity<Collection<StudentDTO>> getAllStudentsByFacultyId(@PathVariable Long idF) {
        return ResponseEntity.ok(houseService.getAllStudentsByFacultyId(idF));
    }
}
