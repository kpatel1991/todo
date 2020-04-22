package com.kunjan.todo.todo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ToDo {

    private long id;
    private String title;
    private String description;
    private boolean isCompleted;
    private Date createdAt;
    private Date dueAt;
}
