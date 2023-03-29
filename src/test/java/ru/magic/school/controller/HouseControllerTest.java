package ru.magic.school.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.magic.school.config.DockerConfig;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Testcontainers
public class HouseControllerTest  extends DockerConfig {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    private Faculty faculty = new Faculty();

    private JSONObject jsonObject= new JSONObject();

    @BeforeEach
    public void setUp() throws JSONException {
        faculty.setName("Griffindor");
        faculty.setColor("Red");
        facultyRepository.save(faculty);

        jsonObject.put("name", "Cogtevran");
        jsonObject.put("color", "bronze");

        Student student1 = new Student();
        student1.setName("Masha");
        student1.setAge(20);
        student1.setFacultyId(faculty);
        studentRepository.save(student1);
        Student student2 = new Student();
        student2.setName("Nina");
        student2.setAge(19);
        student2.setFacultyId(faculty);
        studentRepository.save(student2);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        faculty.setStudentList(students);
    }

    @Test
    public void testCreateFacultyMethod() throws Exception {
        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idF").isNotEmpty())
                .andExpect(jsonPath("$.idF").isNumber())
                .andExpect(jsonPath("$.name").value("Cogtevran"))
                .andExpect(jsonPath("$.color").value("bronze"));

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].name").value("Cogtevran"))
                .andExpect(jsonPath("$[1].color").value("bronze"));
    }

    @Test
    public void testUpdateFacultyMethod() throws Exception {
        jsonObject.put("name", "Slizerin");
        jsonObject.put("color", "green");

        mockMvc.perform(patch("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idF").isNotEmpty())
                .andExpect(jsonPath("$.idF").isNumber())
                .andExpect(jsonPath("$.name").value("Slizerin"))
                .andExpect(jsonPath("$.color").value("green"));

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].name").value("Slizerin"))
                .andExpect(jsonPath("$[1].color").value("green"));
    }

    @Test
    public  void testGetFacultyMethod() throws Exception {
        mockMvc.perform(get("/faculty/" + faculty.getIdF()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Griffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    public void testDeleteFacultyMethod() throws Exception {
        studentRepository.deleteAll();

        mockMvc.perform(delete("/faculty/" + faculty.getIdF()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testGetFacultyListMethod() throws Exception {
        mockMvc.perform(get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testReturneListFacultyValidColorMethod() throws  Exception {
        String color = "Red";

        mockMvc.perform(get("/faculty?validColor=" + color))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(faculty.getName()))
                .andExpect(jsonPath("$[0].color").value(faculty.getColor()));
    }

    @Test
    public void testRetutneListFacultyByNameIgnoreCaseMethod() throws  Exception {
        mockMvc.perform(get("/faculty?name=" + faculty.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(faculty.getName()))
                .andExpect(jsonPath("$[0].color").value(faculty.getColor()));
    }

    @Test
    public void testReturneListAllStudentByFacultyId() throws  Exception {
        mockMvc.perform(get("/faculty/" + faculty.getIdF() + "/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(faculty.getStudentList().size()));
    }

    //


}
