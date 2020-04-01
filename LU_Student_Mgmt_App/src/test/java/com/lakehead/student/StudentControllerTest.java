package com.lakehead.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    //method to test create student
    @Test
    public void createStudent() throws Exception {

        Student s1= new Student();
        s1.setName("BBBB");
        s1.setDegreeType("Undergraduate");
        s1.setDepartment("Computer Science");
        s1.setEmail("bbbb@lakeheadu.ca");
        s1.setFeesPendingAmount((float) 0.0);
        s1.setFeeStatus(true);
        s1.setPercentageAchieved((float)92.0);
        String jsonObj = asJsonString(s1);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/LU/addStudent")
                .content(jsonObj)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //method to test create student
    @Test
    public void updateStudent() throws Exception {

        Student s1= new Student();
        s1.setId(1);
        s1.setName("BBBB");
        s1.setDegreeType("Undergraduate");
        s1.setDepartment("Computer Science");
        s1.setEmail("bbbb@lakeheadu.ca");
        s1.setFeesPendingAmount((float) 0.0);
        s1.setFeeStatus(true);
        s1.setPercentageAchieved((float)92.0);
        String jsonObj = asJsonString(s1);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/LU/student/{id}", 1)
                .content(jsonObj)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    //method to test getAllStudents
    @Test
    public void getAllStudents() throws Exception {
        Student s1= new Student();
        s1.setId(1);
        s1.setName("AAAA");
        s1.setDegreeType("Graduate");
        s1.setDepartment("Computer Science");
        s1.setEmail("aaaa@lakeheadu.ca");
        s1.setFeesPendingAmount((float) 0.0);
        s1.setFeeStatus(true);
        s1.setPercentageAchieved((float)92.0);
        Mockito.when(
                studentService.retrieveAllStudents()).thenReturn(Collections.singleton(s1));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/LU/students").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"id\":1,\"name\":\"AAAA\"}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
    //method to test Student by by id
    @Test
    public void retrieveStudent() throws Exception {
        Student s1= new Student();
        s1.setId(1);
        s1.setName("AAAA");
        s1.setDegreeType("Graduate");
        s1.setDepartment("Computer Science");
        s1.setEmail("aaaa@lakeheadu.ca");
        s1.setFeesPendingAmount((float) 0.0);
        s1.setFeeStatus(true);
        s1.setPercentageAchieved((float)92.0);
        int id = 1;
        Mockito.when(
                studentService.retrieveStudent(id)).thenReturn(s1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/LU/student/"+id).accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"email\":\"aaaa@lakeheadu.ca\",\"degreeType\":\"Graduate\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    
    @Test
    public void deleteStudent() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/LU/student/{id}", 1) )
                .andExpect(status().isOk());
    }

    
}