package org.todo.model;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TaskResponse {
    private long taskId;
    private String title;
    private String description;
    private String category;
    private ZonedDateTime dueDate;
    private String priority;
    private String taskStatus;
    private Long userId;
}
