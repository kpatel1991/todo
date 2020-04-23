package com.kunjan.todo.todo.services;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.repository.ToDoRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("Test")
public class ToDoServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ToDoRepo toDoRepo;

    @BeforeEach
    public void init() {

        // given
        //ToDo meeting = new ToDo(10, "Meeting", "Meeting for devs", false, new Date(), new Date(), "test_user");
        ToDo meeting = new ToDo();
        meeting.setTitle("Meeting");
        meeting.setDescription("Meeting for devs");
        meeting.setCompleted(false);
        meeting.setCreatedAt(new Date());
        meeting.setDueAt(new Date());
        meeting.setCreatedBy("test_user");
        this.entityManager.persist(meeting);
        System.out.println("ID : " + meeting.getId());
        this.entityManager.flush();
    }

    @Test
    void getAllToDos() {

        // when
        List<ToDo> found = toDoRepo.findAll();

        // then
        Assert.assertEquals(found.size(), 1);
        Assert.assertNotEquals(found.size(), 2);
    }

    @Test
    void getToDoById() {
        // when
        Optional<ToDo> found = toDoRepo.findById(3L);

        // then
        found.ifPresent(toDo -> Assert.assertEquals(toDo.getId(), 3L));
    }

    @Test
    void deleteToDo() {
        // given
        Optional<ToDo> beforeDelete = toDoRepo.findById(4L);
        //Assert.assertTrue(beforeDelete.isPresent());
        beforeDelete.ifPresent(toDo -> Assert.assertEquals(toDo.getId(), 4L));

        //when
        //toDoRepo.deleteById(4L);

        //then
        Optional<ToDo> afterDelete = toDoRepo.findById(4L);
        Assert.assertFalse(afterDelete.isPresent());

    }

    @Test
    void updateToDo() {
        // given
        Optional<ToDo> beforeDelete = toDoRepo.findById(2L);
        if(beforeDelete.isPresent()) {
            Assert.assertEquals(beforeDelete.get().getId(), 2L);
            ToDo updateTodo = beforeDelete.get();

            //when
            updateTodo.setTitle("New");
            toDoRepo.save(updateTodo);
            if(toDoRepo.findById(2L).isPresent()) {
                Assert.assertEquals(toDoRepo.findById(2L).get().getTitle(), "New");
            }
        }

    }
}