package com.example.project1.repository;

import com.example.project1.dto.ItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<ItemDto, Integer>
{
    List<ItemDto> findByUserid(String userid);
}
