package com.kunjan.todo.todo.repository;

import com.kunjan.todo.todo.domain.ToDo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("Test")
public class ToDoRepoTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ToDoRepo toDoRepo;

    @Test
    void getAllTodosTest(){
        // given
        //ToDo meeting = new ToDo(10, "Meeting", "Meeting for devs", false, new Date(), new Date(), "test_user");
        ToDo meeting = new ToDo();
        meeting.setTitle("Meeting");
        meeting.setDescription("Meeting for devs");
        meeting.setCompleted(false);
        meeting.setCreatedAt(new Date());
        meeting.setDueAt(new Date());
        meeting.setCreatedBy("test_user");
        entityManager.persist(meeting);
        entityManager.flush();

        // when
        List<ToDo> found = toDoRepo.findAll();

        // then
        Assert.assertEquals(found.size(), 1);
        Assert.assertNotEquals(found.size(), 2);

    }
}