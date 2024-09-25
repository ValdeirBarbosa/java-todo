package com.valdeir.java_todo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){

        var userExists = this.iUserRepository.findUserByUserName(userModel.getName());

        if(userExists != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user name is already in use!");
        }

        var passwordHasred = BCrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHasred);

        var userCreated = this.iUserRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);


    }
    
}
