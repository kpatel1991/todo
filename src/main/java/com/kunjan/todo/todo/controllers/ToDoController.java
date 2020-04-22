package com.kunjan.todo.todo.controllers;

import com.kunjan.todo.todo.domain.ToDo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ToDoController {

    static ArrayList<ToDo> todos = new ArrayList<>();
    static {

            todos.add(new ToDo(1L, "1 st todo", "something 1", false, new Date(), new Date()));
            todos.add(new ToDo(2L, "2 st todo", "something 2", false, new Date(), new Date()));
    }


    @GetMapping("/todos")
    public List<ToDo> getAllToDos() {
        return ToDoController.todos;
    }

    @GetMapping("/todos/{id}")
    public ToDo getToDoById(@PathVariable Long id) {
        return todos.stream().filter( todo -> id == todo.getId()).findFirst().get();
    }

    @PostMapping("/")
    public String getAllToDos(@RequestBody ToDo toDo) {
        ToDoController.todos.add(toDo);
        return "ToDo added!!";
    }

    @DeleteMapping("/todos/{id}")
    public String deleteToDo(@PathVariable Long id) {
        Optional<ToDo> deleteTodo = ToDoController.todos.stream().filter(todo -> id == todo.getId()).findFirst();
        if(deleteTodo.isPresent()) {
            ToDoController.todos.remove(deleteTodo.get());
        }
        return deleteTodo.isPresent() ? "ToDo deleted!!" : "ToDO desn't Existe";
    }

    @PutMapping("/todos/{id}")
    public String updateToDo(@RequestBody ToDo updateTodo) {
        Optional<ToDo> targetTodo = ToDoController.todos.stream().filter(todo -> updateTodo.getId() == todo.getId()).findFirst();
        if(targetTodo.isPresent()) {
            ToDoController.todos.remove(targetTodo);
            ToDoController.todos.add(updateTodo);
        }
        return targetTodo.isPresent() ? "ToDo deleted!!" : "ToDO desn't Existe";
    }
}
