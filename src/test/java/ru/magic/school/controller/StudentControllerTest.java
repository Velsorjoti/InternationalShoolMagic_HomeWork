package ru.magic.school.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.magic.school.config.DockerConfig;
import ru.magic.school.model.Faculty;
import ru.magic.school.model.Student;
import ru.magic.school.repository.FacultyRepository;
import ru.magic.school.repository.StudentRepository;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Testcontainers
public class StudentControllerTest extends DockerConfig {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    StudentRepository studentRepository;
    private Student student = new Student();
    private Faculty faculty = new Faculty();
    private JSONObject jsonObject= new JSONObject();
    @Autowired
    FacultyRepository facultyRepository;

    @BeforeEach
    public void setUp() throws JSONException {
        faculty.setName("Puffendui");
        faculty.setColor("yellow");
        facultyRepository.save(faculty);

        jsonObject.put("name", "Medz");
        jsonObject.put("age", 20);
        jsonObject.put("facultyId", faculty.getIdF());

        student.setName("Nina");
        student.setAge(19);
        student.setFacultyId(faculty);
        studentRepository.save(student);
    }

    @Test
    void testCreateStudentMethod() throws Exception {
        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idS").isNotEmpty())
                .andExpect(jsonPath("$.idS").isNumber())
                .andExpect(jsonPath("$.name").value("Medz"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.facultyId").value(faculty.getIdF()));
        mockMvc.perform(get("/student?page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].name").value("Medz"))
                .andExpect(jsonPath("$[1].age").value(20))
                .andExpect(jsonPath("$[1].facultyId").value(faculty.getIdF()));
    }

    @Test
    void testGetStudentMethod() throws Exception {
        studentRepository.delete(student);
        mockMvc.perform(get("/student" + student.getIdS()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateStudentMethod() throws Exception {
        jsonObject.put("name","Zdem");
        jsonObject.put("age", 21);
        mockMvc.perform(patch("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idS").isNotEmpty())
                .andExpect(jsonPath("$.idS").isNumber())
                .andExpect(jsonPath("$.name").value("Zdem"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.facultyId").value(faculty.getIdF()));
        mockMvc.perform(get("/student?page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[1].name").value("Zdem"))
                .andExpect(jsonPath("$[1].age").value(21))
                .andExpect(jsonPath("$[1].facultyId").value(faculty.getIdF()));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/" + student.getIdS()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/student?page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testReturneListOfStudent() throws  Exception {
        mockMvc.perform(get("/student?page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testReturneListOfStudentValidAgeMethod() throws Exception {
        mockMvc.perform(get("/student?age=19&page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(student.getName()))
                .andExpect(jsonPath("$[0].age").value(student.getAge()));
    }

    @Test
    void testReturneListOfStudentBetweenAgeMethod() throws Exception {
        mockMvc.perform(get("/student?min=17&max=30&page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value(student.getName()))
                .andExpect(jsonPath("$[0].age").value(student.getAge()));
    }

    @Test
    void  testStudentTotalCountMethod() throws Exception {
        when(Mockito.mock(StudentRepository.class).getAllStudentCount()).thenReturn(1L);
        mockMvc.perform(get("/student/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void testStudentAverageAgeMethod() throws Exception  {
        mockMvc.perform(get("/student/avg"))
                .andExpect(status().isOk())
                .andExpect(content().string(studentRepository.getAverageAge().toString()));
    }

    @Test
    void testStudentFiveYoungMethod() throws Exception {
        mockMvc.perform(get("/student/young"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


//

}
