package com.kunjan.todo.todo.controllers;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ToDoController {


    @Autowired
    private ToDoService toDoService;

    @GetMapping("/todos")
    public List<ToDo> getAllToDos() {
        return toDoService.getAllTodos();
    }

    @GetMapping("/todos/{id}")
    public ToDo getToDoById(@PathVariable Long id) {
        return toDoService.getToDoById(id);
    }

    @PostMapping("/")
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/todos/{id}")
    public boolean deleteToDo(@PathVariable Long id) {
        return toDoService.deleteToDo(id);
    }

    @PutMapping("/todos/{id}")
    public ToDo updateToDo(@RequestBody ToDo updateTodo) {
        return toDoService.updateToDo(updateTodo);
    }
}