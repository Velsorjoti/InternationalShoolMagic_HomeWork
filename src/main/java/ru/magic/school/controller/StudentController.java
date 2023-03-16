package ru.magic.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.magic.school.DTO.FacultyDTO;
import ru.magic.school.DTO.StudentDTO;
import ru.magic.school.model.Avatar;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.service.AvatarService;
import ru.magic.school.service.HouseService;
import ru.magic.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/student")
@RestController
public class StudentController {
    private StudentService studentService;
    private HouseService houseService;
    private AvatarService avatarService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("{idS}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long idS) {
        StudentDTO studentDTO = studentService.getStudentByIdS(idS);
        if(studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentDTO);
    }

    @PatchMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) {
       StudentDTO updateStudentDTO = studentService.updateStudent(studentDTO);
       if (updateStudentDTO == null) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
       return ResponseEntity.ok(updateStudentDTO);
    }

    @DeleteMapping("{idS}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long idS) {
        studentService.deleteStudent(idS);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDTO>> getStudent(@RequestParam(required = false) Integer age, @RequestParam(required = false) Integer min,@RequestParam(required = false) Integer max,@RequestParam Integer page, @RequestParam Integer pageSize) {
        if (age != null)
        return ResponseEntity.ok(studentService.validStudentByAge(age));
        if (min != null && max != null) {
            return  ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        if (pageSize <= 0 || pageSize > 50){
         return ResponseEntity.ok(studentService.getAllStudent(page, 50));
        }
        return ResponseEntity.ok(studentService.getAllStudent(page, pageSize));
    }


    @GetMapping("{idS}/faculty")
    public ResponseEntity<FacultyDTO> getFacultyByIdStudent(@PathVariable Long idS) {
        return ResponseEntity.ok(studentService.getFacultyByIdStudent(idS));
    }

    @PostMapping(value = "{idS}/student/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable  Long idS, @RequestParam MultipartFile avatar) throws IOException{
        if (avatar.getSize() >= 1024 * 300) {
            return  ResponseEntity.badRequest().body("Файл слишком большой");
        }
        avatarService.uploadAvatar(idS,avatar);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{idA}/avatar/data")
    public ResponseEntity<byte[]> downLoadAvatar(@PathVariable Long idA) {
        Avatar avatarServiceStudentAvatar = avatarService.findStudentAvatar(idA);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(avatarServiceStudentAvatar.getMediaType()));
        httpHeaders.setContentLength(avatarServiceStudentAvatar.getData().length);



        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(avatarServiceStudentAvatar.getData());
    }
    @GetMapping(value = "/{idA}/avatar")
    public void downloadAvatar(@PathVariable Long idA, HttpServletResponse response) throws IOException {
        Avatar avatar1 = avatarService.findStudentAvatar(idA);

        Path path = Path.of(avatar1.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar1.getMediaType());
            response.setContentLength((int) avatar1.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping(value = "student/count")
    public ResponseEntity<Long> getAllStudentCount() {
        return ResponseEntity.ok(studentService.getAllStudentCount());
    }

    @GetMapping(value = "student/avg")
    public ResponseEntity<Long> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping(value = "student/young")
    public ResponseEntity<Collection<Student>> getFistFiveYoungStudent() {
        return ResponseEntity.ok(studentService.getFistFiveYoungStudent());
    }
}
