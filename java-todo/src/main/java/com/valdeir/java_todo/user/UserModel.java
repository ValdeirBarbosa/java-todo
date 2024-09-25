package com.valdeir.java_todo.user;

import lombok.Data;
import java.util.UUID;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name="tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator="UUID")
    private UUID id;

    @Column(unique=true)
    private String userName;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
