package org.todo.model;

import lombok.Data;
import org.todo.model.enums.Category;
import org.todo.model.enums.Priority;
import org.todo.model.enums.TaskStatus;

import java.time.ZonedDateTime;

@Data
public class TaskRequest {
    private long taskId;
    private String title;
    private String description;
    private Category category;
    private ZonedDateTime dueDate;
    private Priority priority;
    private TaskStatus taskStatus;
    private Long userId;

}
