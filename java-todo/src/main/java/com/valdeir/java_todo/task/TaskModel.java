package com.valdeir.java_todo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="tb_task")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private UUID idUser;

    private String title;
    private String description;
    private String priority;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @CurrentTimestamp
    private LocalDate createdAt;
    
}
