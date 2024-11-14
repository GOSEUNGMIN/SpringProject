package com.example.project1.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserDto
{
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // pk설정
    private String id;
    private String password;
    private String username;
}
