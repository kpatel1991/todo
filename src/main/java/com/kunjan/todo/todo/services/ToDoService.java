package com.kunjan.todo.todo.services;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepo toDoRepo;

    public List<ToDo> getAllTodos() {
        return toDoRepo.findAll();
    }

    public ToDo getToDoById(Long id) {
        return toDoRepo.findById(id).orElse(null);
    }

    public boolean deleteToDo(Long id) {
        Optional<ToDo> todo = toDoRepo.findById(id);
        if(todo.isPresent()) {
            toDoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public ToDo updateToDo(ToDo updateTodo) {
        return toDoRepo.save(updateTodo);
    }
}
