package com.example.project1.repository;

import com.example.project1.dto.ItemDto;
import com.example.project1.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepo extends JpaRepository<UserDto, String> // dto를 가져와 어디다 담을지
{
    Optional<UserDto> findByIdAndPassword(String id, String password);
}
