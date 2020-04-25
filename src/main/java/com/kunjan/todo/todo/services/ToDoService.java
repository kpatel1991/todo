package com.kunjan.todo.todo.services;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.exception.NotFoundException;
import com.kunjan.todo.todo.repository.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ToDo getToDoById(Long id) {
        return toDoRepo.findById(id).orElseThrow(() -> new NotFoundException("ToDo Not Found!"));
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
        return todo.orElseThrow(() -> new NotFoundException("ToDo Not Found!"));
    }
}
