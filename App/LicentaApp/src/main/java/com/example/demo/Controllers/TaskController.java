package com.example.demo.Controllers;


import au.com.bytecode.opencsv.CSVReader;
import com.example.demo.BLL.TaskService;
import com.example.demo.BLL.exceptions.InvalidIdException;
import com.example.demo.BLL.exceptions.InvalidTaskException;
import com.example.demo.BLL.exceptions.InvalidUserException;
import com.example.demo.Model.DTOs.TaskDTO;
import com.example.demo.Model.Task;
import com.example.demo.Model.enums.Severity;
import com.example.demo.Model.enums.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//controller
@RestController
//link the browser to the server
@CrossOrigin
//map web requests to controller methods
@RequestMapping(TaskController.BASE_URL)
public class TaskController {
    protected static final String BASE_URL = "task_prioritization/tasks";

    private final TaskService taskService;

    public TaskController(TaskService taskBLL) {
        this.taskService = taskBLL;
    }

    @RequestMapping(value = "/allTasksById/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasksByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(taskService.getTasksByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/allTasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) throws InvalidUserException, InvalidTaskException {
        return new ResponseEntity<>(taskService.addTask(taskDTO), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/update/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<Task> updateTaskStatus(@PathVariable int taskId, @RequestBody Task task) throws InvalidIdException {
        return new ResponseEntity<>(taskService.updateTaskStatus(taskId, task), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{taskId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/complete/{taskId}", method = RequestMethod.PUT)
    public ResponseEntity<String> completeTask(@PathVariable int taskId) throws InvalidIdException {
        taskService.completetask(taskId);
        return new ResponseEntity<String>("Successfully completed", HttpStatus.OK);
    }

}
