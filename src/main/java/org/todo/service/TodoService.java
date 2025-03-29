package org.todo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.todo.model.TaskRequest;
import org.todo.model.TaskResponse;
import org.todo.model.TodoDao;
import org.todo.repository.TodoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private TodoRepository todoRepository;

    private static TaskResponse getResponse(TodoDao dao) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskId(dao.getId());
        taskResponse.setTitle(dao.getTitle());
        taskResponse.setDescription(dao.getDescription());
        taskResponse.setCategory(dao.getCategory().name());
        taskResponse.setDueDate(dao.getDueDate());
        taskResponse.setPriority(dao.getPriority().name());
        taskResponse.setTaskStatus(dao.getTaskStatus().name());
        taskResponse.setUserId(dao.getUserId());
        return taskResponse;
    }

    public List<TaskResponse> getAllTodo(Long userId) {
        List<TodoDao> todoDaoList = todoRepository.findAllByUserIdAndActiveIsTrueOrderByPriorityDesc(userId);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        todoDaoList.forEach(dao -> {
            TaskResponse taskResponse = getResponse(dao);
            taskResponseList.add(taskResponse);
        });
        // here we use sort method
        taskResponseList.sort(Comparator.comparing(TaskResponse::getPriority).reversed());
        return taskResponseList;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        TodoDao todoDao = new TodoDao();
        todoDao.setActive(true);
        todoDao.setTitle(taskRequest.getTitle());
        todoDao.setDescription(taskRequest.getDescription());
        todoDao.setCategory(taskRequest.getCategory());
        todoDao.setDueDate(taskRequest.getDueDate());
        todoDao.setPriority(taskRequest.getPriority());
        todoDao.setTaskStatus(taskRequest.getTaskStatus());
        todoDao.setUserId(taskRequest.getUserId());
        TodoDao dao = todoRepository.save(todoDao);

        return getResponse(dao);
    }

    public TaskResponse updateTask(TaskRequest taskRequest, Long userId) {
        TodoDao todoDao = todoRepository.findByUserIdAndId(userId, taskRequest.getTaskId());
        if (todoDao == null) {
            throw new IllegalArgumentException("Task not found for given user and task id.");
        }
        todoDao.setTitle(taskRequest.getTitle());
        todoDao.setCategory(taskRequest.getCategory());
        todoDao.setDueDate(taskRequest.getDueDate());
        todoDao.setPriority(taskRequest.getPriority());
        todoDao.setTaskStatus(taskRequest.getTaskStatus());
        todoDao.setUserId(taskRequest.getUserId());
        TodoDao dao = todoRepository.save(todoDao);
        return getResponse(dao);
    }


//new for put mapping


    public TaskResponse updateTaskField(TaskRequest taskRequest, Long userId) {
        TodoDao todoDao = todoRepository.findByUserIdAndId(userId, taskRequest.getTaskId());

        if (todoDao == null) {
            throw new IllegalArgumentException("Task not found for given user and task id.");
        }

        if (taskRequest.getTitle() != null) {
            todoDao.setTitle(taskRequest.getTitle());
        }
        todoDao.setCategory(taskRequest.getCategory());
        todoDao.setDueDate(taskRequest.getDueDate());
        todoDao.setPriority(taskRequest.getPriority());
        todoDao.setTaskStatus(taskRequest.getTaskStatus());
        todoDao.setUserId(taskRequest.getUserId());
        TodoDao dao = todoRepository.save(todoDao);
        return getResponse(dao);
    }

    public void deleteTask(Long userId, Long taskId) {
        TodoDao todoDao = todoRepository.findByUserIdAndId(userId, taskId);
        if (todoDao == null) {
            throw new IllegalArgumentException("Task not found for given user and task id.");
        }

        todoRepository.delete(todoDao);

        if (todoDao.isActive()) {
            todoDao.setActive(false);
            todoRepository.save(todoDao);
        } else {
            throw new IllegalArgumentException("already data available");
        }
    }
}
