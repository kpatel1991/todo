package com.kunjan.todo.todo.services;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepo toDoRepo;

    public List<ToDo> getAllToDos() {
        return toDoRepo.findAll();
    }

    public List<ToDo> getToDoPage(int pageNo) {

        // check pageNo > 0 --> Invalid page Error

        if(pageNo <= 0) {
            return null;
        }

        Pageable pageable = PageRequest.of(pageNo - 1, 10);
        Page<ToDo> userPage = toDoRepo.findAll(pageable);

        // check userPage.getContent != null --> Page Index out of Bound Error.

        return userPage.getContent().isEmpty() ? null : userPage.getContent();
    }

    public ToDo getToDoById(Long id) {
        return toDoRepo.findById(id).orElse(null);
    }

    public boolean deleteToDo(Long id) {
        if(getAvailableTodo(id) != null) {
            toDoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public ToDo updateToDo(ToDo updateTodo) {
        if (updateTodo.getId() == 0){
            updateTodo.setCreatedAt(new Date());
        }
        return toDoRepo.save(updateTodo);
    }

    private ToDo getAvailableTodo(Long id) {
        Optional<ToDo> todo = toDoRepo.findById(id);
        return todo.orElse(null);
    }
}
