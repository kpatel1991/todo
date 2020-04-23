package com.kunjan.todo.todo.repository;

import com.kunjan.todo.todo.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long> {

}
