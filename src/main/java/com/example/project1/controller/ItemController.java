package com.example.project1.controller;

import com.example.project1.dto.ItemDto;
import com.example.project1.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController
{
    private final TaskService taskService;
    @GetMapping
    public String Item(Model model)
    {
        List<ItemDto> itemList = taskService.list();
        model.addAttribute("itemlist", itemList);
        return "Item/Item";
    }

}
