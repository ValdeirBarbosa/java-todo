package com.valdeir.java_todo.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository iTaskRepository;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
       taskModel.setIdUser((UUID) request.getAttribute("userId")); 
       
       var currentDate = LocalDateTime.now();
       if(currentDate.isAfter(taskModel.getStartAt()) ||
          currentDate.isAfter(taskModel.getEndAt())   ||
          taskModel.getEndAt().isBefore(taskModel.getStartAt())){ 
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The end task date and start date  must be greater than end current date! ");
        }
        
        var taskCreated = this.iTaskRepository.save(taskModel);
        return  ResponseEntity.status(HttpStatus.OK).body(taskCreated);
    }
    
    
    @GetMapping("/") 
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("userId");
        var tasks =  this.iTaskRepository.findByIdUser((UUID) idUser);
        
        return tasks;
    }
    
}