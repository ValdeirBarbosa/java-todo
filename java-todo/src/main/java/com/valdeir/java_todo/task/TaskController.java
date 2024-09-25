package com.valdeir.java_todo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;
    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel) {
        taskModel.setPriority(taskModel.getPriority().toUpperCase());
       var taskCreated = this.iTaskRepository.save(taskModel);
        
        return taskCreated;
    }
    
    
}
