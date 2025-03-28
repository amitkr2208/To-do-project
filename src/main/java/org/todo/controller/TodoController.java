package org.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.todo.model.TaskRequest;
import org.todo.model.TaskResponse;
import org.todo.model.TodoDao;
import org.todo.repository.TodoRepository;
import org.todo.service.TodoService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {
    private TodoService todoService;

    @GetMapping("/{userId}")
    public List<TaskResponse> getTodo(@PathVariable Long userId) {
        return todoService.getAllTodo(userId);
    }

    @PostMapping("/{userId}")
    public TaskResponse createTodo(@RequestBody TaskRequest taskRequest) {
        return todoService.createTask(taskRequest);
    }

    @PutMapping("/{userId}")
    public TaskResponse UpdateTask(@PathVariable Long userId, @RequestBody TaskRequest taskRequest) {
        return todoService.updateTask(taskRequest, userId);
    }


    @PatchMapping("/{userId}")
    public TaskResponse updateTaskField(@PathVariable Long userId, @RequestBody TaskRequest taskRequest) {
        return todoService.updateTaskField( taskRequest,userId);
    }


    @DeleteMapping("/{userId}/Tasks/{taskId}")
    public void deleteTaskByUserId(@PathVariable Long userId, @PathVariable Long taskId) {

        todoService.deleteTask(userId,taskId);
    }
}

