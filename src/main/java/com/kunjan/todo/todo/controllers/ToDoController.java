package com.kunjan.todo.todo.controllers;

import com.kunjan.todo.todo.domain.ToDo;
import com.kunjan.todo.todo.services.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ToDoController {


    @Autowired
    private ToDoService toDoService;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHello() {
        ArrayList<String> strs = new ArrayList<>();

        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<ToDo>> getAllToDos() {
        List<ToDo> toDos = toDoService.getAllToDos();
        return new ResponseEntity<>(toDos, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getToDoById(id), HttpStatus.OK);
    }

    @PostMapping("/todos")
    public ResponseEntity<ToDo> createToDo(@RequestBody ToDo toDo) {
        return new ResponseEntity<>(toDoService.updateToDo(toDo), HttpStatus.CREATED);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Boolean> deleteToDo(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.deleteToDo(id), HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<ToDo> updateToDo(@RequestBody ToDo updateTodo) {
        return new ResponseEntity<>(toDoService.updateToDo(updateTodo), HttpStatus.CREATED);
    }
}
