package com.kunjan.todo.todo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String title;

    private String description;

    private boolean isCompleted;

    private Date createdAt;

    private Date dueAt;

    @NotEmpty
    private String createdBy;

    public ToDo() {  }

}
