package org.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.todo.model.enums.Category;
import org.todo.model.enums.Priority;
import org.todo.model.enums.TaskStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo")
public class TodoDao {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    private Long createdBy;

    private Long updatedBy;
    // main fields
    private Priority priority;
    private ZonedDateTime dueDate;
    private TaskStatus taskStatus;
    private String taskName;
    private Category category;
    private Long userId;
    private String title;
    private String description;
    private boolean active;




    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = ZonedDateTime.now();
        if (createdBy == null) {
            createdBy = updatedBy = 0L;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = ZonedDateTime.now();
        updatedBy = 0L;
    }
}
