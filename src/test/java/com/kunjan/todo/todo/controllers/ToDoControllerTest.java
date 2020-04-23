package com.kunjan.todo.todo.controllers;

import com.google.gson.Gson;
import com.kunjan.todo.todo.domain.ToDo;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    void createToDo() throws Exception {
        String todo1 = new JSONObject()
                        .put("title", "TODO1")
                        .put("description", "Optional")
                        .put("createdBy", "testUser")
                        .put("dueAt", "2020-04-27").toString();

        MvcResult result = mockMvc.perform(post("/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(todo1))
                            .andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
        Assert.assertNotNull(result.getResponse().getContentAsString());

    }

    @Test
    void getAllToDos() throws Exception {

        createToDo();

        MvcResult result = mockMvc.perform(get("/todos")).andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
        Assert.assertNotEquals(result.getResponse().getContentAsString(), "[]");

    }

    @Test
    void getToDoById() throws Exception{

        MvcResult result = mockMvc.perform(get("/todos/1")).andReturn();

        Assert.assertEquals(result.getResponse().getStatus(), 200);
        Gson g = new Gson();
        ToDo response = g.fromJson(result.getResponse().getContentAsString(), ToDo.class);
        Assert.assertEquals(response.getId(), 1);
        Assert.assertEquals(response.getTitle(), "TODO1");
        Assert.assertEquals(response.getCreatedBy(), "testUser");
        Assert.assertFalse(response.isCompleted());
    }

    @Test
    void deleteToDo() throws  Exception{
        MvcResult result1 = mockMvc.perform(delete("/todos/1")).andReturn();

        Assert.assertEquals(result1.getResponse().getStatus(), 200);
        Assert.assertEquals(result1.getResponse().getContentAsString(), "true");

        MvcResult result2 = mockMvc.perform(delete("/todos/1")).andReturn();

        Assert.assertEquals(result2.getResponse().getStatus(), 200);
        Assert.assertEquals(result2.getResponse().getContentAsString(), "false");
    }

    /*@Test
    void updateToDo() throws Exception{
        String todo1 = new JSONObject()
                .put("description", "Updated").toString();
        MvcResult result = mockMvc.perform(put("/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todo1))
                .andReturn();
        Assert.assertEquals(result.getResponse().getStatus(), 200);
        Gson g = new Gson();
        ToDo response = g.fromJson(result.getResponse().getContentAsString(), ToDo.class);
        Assert.assertEquals(response.getId(), 1);
        Assert.assertEquals(response.getTitle(), "TODO1");
        Assert.assertEquals(response.getCreatedBy(), "testUser");
        Assert.assertEquals(response.getDescription(), "Updated");
        Assert.assertFalse(response.isCompleted());
    }*/
}