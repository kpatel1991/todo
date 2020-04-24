package com.kunjan.todo.todo.services;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.repository.ToDoRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.*;

/**
 * Our Service layer code is dependent on our Repository.
 * However, to test the Service layer, we do not need to know
 * or care about how the persistence layer is implemented:
 * <p>
 * e.g We should be able to write and test our Service layer
 * code without wiring in our full persistence layer.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ToDoService.class, ToDoRepo.class })
class ToDoServiceTest {

    @Autowired
    private ToDoService toDoService;

    @MockBean
    private ToDoRepo toDoRepo;

    @BeforeEach
    public void setup() {
        ToDo test1 = new ToDo(
                1,
                "Test1",
                "Test todo1",
                false,
                new Date(),
                new Date(),
                "test_user1");

        ToDo test2 = new ToDo(
                2,
                "Test2",
                "Test todo2",
                true,
                new Date(),
                new Date(),
                "test_user2");

        Mockito.when(toDoRepo.findById(test1.getId()))
                .thenReturn(Optional.of(test1));

        Mockito.when(toDoRepo.findById(test2.getId()))
                .thenReturn(Optional.of(test2));

        Mockito.when(toDoRepo.findAll())
                .thenReturn(Arrays.asList(test1, test2));

        Mockito.when(toDoRepo.save(test1))
                .thenReturn(test1);

        Mockito.when(toDoRepo.save(test2))
                .thenReturn(test2);
    }

    @Test
    void getAllToDos() {
        List<ToDo> todos = toDoService.getAllToDos();

        Assert.assertEquals(todos.size(), 2);

    }

    @Test
    void getToDoById() {

        ToDo found = toDoService.getToDoById(1L);

        Assert.assertNotNull(found);
        Assert.assertEquals("Test1", found.getTitle());
        Assert.assertEquals("Test todo1", found.getDescription());
        Assert.assertEquals("test_user1", found.getCreatedBy());
        Assert.assertFalse(found.isCompleted());

    }

    @Test
    void deleteToDo() {
        boolean deleted = toDoService.deleteToDo(2L);

        Assert.assertTrue(deleted);
    }

    @Test
    void updateToDo() {
        //TODO
    }
}